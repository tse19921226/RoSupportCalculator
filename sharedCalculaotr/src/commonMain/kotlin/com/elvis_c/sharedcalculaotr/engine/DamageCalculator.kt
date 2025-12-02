package com.elvis_c.sharedcalculaotr.engine

import com.elvis_c.sharedcalculaotr.equipment.model.WeaponType
import kotlin.math.floor
import kotlin.math.max

/**
 * 傷害計算核心
 *
 * 整體結構依照「攻擊力、防禦力系統整合」文章整理：
 * 1. 前段 ATK / MATK
 * 2. 後段 ATK / MATK（種族 / 體型 / 階級 / 屬性增傷與減傷）
 * 3. 全段 ATK / MATK（P.ATK、近/遠、爆擊、對喵族…）
 * 4. 技能倍率區
 * 5. DEF / MDEF 減傷
 * 6. 技能增傷
 * 7. 最終特殊乘區（潛擊、爪痕、紋章、金剛、俯身等等）
 *
 * 所有「％數」一律用小數，例如：30% 請填 0.3。
 */
object DamageCalculator {

    // ---------- 共用小工具 ----------

    /** 把 30% 這種小數(0.3)轉成 1.3 這種乘數 */
    private fun onePlus(pct: Double): Double = 1.0 + pct

    /** 多個乘數相乘；空集合時回傳 1.0 */
    private fun product(values: Iterable<Double>): Double =
        values.fold(1.0) { acc, v -> acc * v }

    // ---------- 物理傷害相關 ----------

    /**
     * 物理攻擊距離類型
     */
    enum class AttackRange {
        Melee,  // 近戰
        Ranged  // 遠程
    }

    /**
     * 物理傷害計算所需參數
     *
     * 所有百分比請用「小數」表示，例如 30% = 0.3。
     *
     * 大部分欄位給預設 0.0，這樣你可以只填你有用到的部分。
     */
    data class PhysicalParams(
        // --- 基礎 ATK ---

        /** 前段總 ATK：例如 素質 ATK ×2 + 修練 ATK + 強悍 ATK… 已加總完成。 */
        val frontAtk: Double,

        /**
         * 武器 ATK（已經算好基礎 ATK + 精煉 + 打擊強化 ±浮動）
         *
         * 若你想完全照文章，可以先用下面的 helper：
         *   calcStrWeaponAtk(...) / calcDexWeaponAtk(...)
         */
        val weaponAtk: Double,

        /** 卡片、裝備提供的額外 ATK（會併入後段 ATK 計算） */
        val cardAtk: Double = 0.0,

        /** [點穴 ‧ 半] 的額外 ATK，加在後段 base 裡 */
        val dianXueHalfAtk: Double = 0.0,

        /** 加農砲 / 砲彈 ATK（只有機械工匠、基因學者用得到） */
        val cannonAtk: Double = 0.0,

        // --- 攻擊方式 / 武器 / 屬性 ---

        /** 武器種類（用於體型修正、雙手判斷等） */
        val weaponType: WeaponType,

        /** 是否使用左手武器（雙刀懲罰 25%） */
        val usingLeftHandWeapon: Boolean = false,

        /** 是否忽略體型懲罰（裝備 or 卡片有「無視體型懲罰」） */
        val ignoreSizePenalty: Boolean = false,

        /** 攻擊距離類型：近戰 or 遠程（影響許多增傷與減傷） */
        val attackRange: AttackRange,

        /** 攻擊屬性 */
        val attackElement: Element,

        /** 攻擊屬性階級（通常 1~4） */
        val attackElementLevel: ElementLevel = ElementLevel.Lv1,

        /** 目標屬性 */
        val targetElement: Element,

        /** 目標屬性階級 */
        val targetElementLevel: ElementLevel = ElementLevel.Lv1,

        /** 目標體型（小 / 中 / 大） */
        val targetSize: MonsterSize,

        /** 此次攻擊是否為爆擊（技能爆擊時需注意爆傷效果減半的規則） */
        val isCritical: Boolean = false,

        // --- 後段 ATK：對種族 / 體型 / 階級 / 屬性 的增傷 ---

        /** 對種族 ATK%（例如「對人形物理傷害 +30%」 → 0.3） */
        val raceAtkPct: Double = 0.0,

        /** 對體型 ATK% */
        val sizeAtkPct: Double = 0.0,

        /** 對怪物屬性 ATK% */
        val targetAttributeAtkPct: Double = 0.0,

        /** 對階級 ATK%（一般 / BOSS） */
        val classAtkPct: Double = 0.0,

        /** 特殊種族增傷（例如 對不死型、對特定魔物 等） */
        val specialRaceAtkPct: Double = 0.0,

        /**
         * 致命塗毒 300%（若啟用請填 3.0，其它情況填 0.0）
         * 對應原文的 (1 + [致命塗毒]300%)
         */
        val edp300Pct: Double = 0.0,

        /**
         * 霸氣 ATK%：對應原文「霸氣ATK%」
         *
         * 注意：原文是
         *   base * (1 + 種族%) * (1 + 體型%) * ...  +  base * (霸氣ATK% + ATK%)
         * 這裡我們把「霸氣ATK% + ATK%」合併為這個欄位，請自行先加總。
         */
        val baxiPlusGenericAtkPct: Double = 0.0,

        // --- 後段 ATK：對方減傷區（體型 / 階級 / 屬性 / 種族）---

        /** 對方體型減傷％（例如「受到小型物理傷害 -30%」 → 0.3） */
        val targetSizeResistPct: Double = 0.0,

        /** 對方階級減傷％ */
        val targetClassResistPct: Double = 0.0,

        /**
         * 攻擊屬性減傷％
         * 再加上萬紫千紅 / 毒耐性弱化 / 毀滅彗星等 debuff 請自行先合併後填入。
         *
         * 例：原本火屬性減傷 30%，又吃到萬紫 +100% → 你可自行換算成有效的 net 值。
         */
        val targetAttackElementResistPct: Double = 0.0,

        /** 目標「怪物屬性」減傷％（原文：怪物屬性減傷） */
        val targetAttributeResistPct: Double = 0.0,

        /** 目標種族減傷％（後段中那個「怪物屬性減傷%」之外的種族減傷） */
        val targetRaceResistPctInFullAtk: Double = 0.0,

        // --- 全段 ATK：P.ATK / 近遠增傷 / 爆傷 / 喵族 / 減傷 ---

        /** P.ATK%：對應原文全段 ATK 裡的 (1 + P.ATK%) */
        val pAtkPct: Double = 0.0,

        /** 近或遠距離物理增傷%（依 attackRange 套用） */
        val rangeDamagePct: Double = 0.0,

        /**
         * 爆擊傷害增傷%（平砍對應 100%+，技能爆擊時請先自行 /2 再塞進來）
         * 例：卡片寫「暴擊傷害+60%」，普通爆擊就填 0.6，技能爆擊就填 0.3。
         */
        val critDamagePct: Double = 0.0,

        /** 對喵族增傷 10% → 填 0.1 */
        val vsDoramPct: Double = 0.0,

        /** 對方 近/遠距離減傷%（依 attackRange 套用） */
        val targetRangeResistPct: Double = 0.0,

        /** 對方種族減傷%（全段 ATK 那行的 (1 - 種族減傷%)） */
        val targetRaceResistPctInFullAtk2: Double = 0.0,

        // --- 技能倍率與技能本身相關增傷 ---

        /**
         * 技能倍率（例如 700% → 7.0）
         * 原文的「技能倍率％ + 集中攻擊 + 凶砍 + 狂怒之槍」會合併成 skillRate + extraSkillRatePct。
         */
        val skillRate: Double,

        /** 額外技能倍率：集中攻擊 / 凶砍 / 狂怒之槍等加在技能倍率上的百分比（小數） */
        val extraSkillRatePctFromBuff: Double = 0.0,

        /** 高階拳刃修練增加的 20% → 填 0.2 */
        val highKatarTrainingPct: Double = 0.0,

        // --- 防禦：RES / DEF ---

        /** 對方 RES（物理韌性） */
        val res: Double = 0.0,

        /** 對方前 DEF（舊的平減 DEF，已除以 100 轉成 0.xx 的話就填小數即可） */
        val frontDef: Double = 0.0,

        /** 對方後 DEF（裝備 DEF 總和） */
        val backDef: Double = 0.0,

        // --- 技能增傷（卡片 / 技能）---

        /** 技能增傷卡片%（例如「某技能傷害 +30%」→ 0.3） */
        val skillDamagePctFromCards: Double = 0.0,

        /** 技能增傷技能%（例如某被動「某技能傷害 +20%」→ 0.2） */
        val skillDamagePctFromSkills: Double = 0.0,

        /** 靈氣劍固定加成（原文：技能等級 × 20 × (1 + 爆擊傷害40% + C.RATE%)）請先算好再填入。 */
        val fixedExtraDamage: Double = 0.0,

        // --- 最終乘區（潛擊 / 爪痕 / 紋章 / 金剛 / 俯身等等）---

        /**
         * 最後階段所有額外乘數：
         * 例如：
         *  (1 + 潛擊30%)、
         *  (1 + 致命爪痕150%)、
         *  (1 + 屬性紋章50%)、
         *  (1 - 金剛90%)、
         *  (1 - 俯身90%)
         *  ... etc
         */
        val finalMultipliers: List<Double> = emptyList(),

        /** Hit 數（技能多段 hit 時使用） */
        val hitCount: Int = 1
    )

    /**
     * 計算物理傷害（單次施放的總傷害，已乘上 hitCount）
     */
    fun calcPhysicalDamage(p: PhysicalParams): Double {
        // 1) 體型修正（若有無視體型則視為 1.0）
        val sizePenalty = SizeModifier.getMultiplier(
            weapon = p.weaponType,
            size = p.targetSize,
            ignorePenalty = p.ignoreSizePenalty
        )

        // 2) 元素相剋倍率（攻擊屬性 vs 防禦屬性）
        val elementMod = ElementModifier.getMultiplier(
            attack = p.attackElement,
            defense = p.targetElement,
            defenseLv = p.targetElementLevel
        )

        // 3) 後段 base：武器ATK + 卡ATK + 點穴(半)
        val baseBackAtk = p.weaponAtk + p.cardAtk + p.dianXueHalfAtk

        // 4) 後段：種族 / 體型 / 階級 / 屬性 / 特殊種族 / 致命塗毒300%
        val offensiveBackMultiplier =
            onePlus(p.raceAtkPct) *
                    onePlus(p.sizeAtkPct) *
                    onePlus(p.edp300Pct) *
                    onePlus(p.targetAttributeAtkPct) *
                    onePlus(p.classAtkPct) *
                    onePlus(p.specialRaceAtkPct)

        // 5) 霸氣 + 一般 ATK%（請事先合併成 baxiPlusGenericAtkPct）
        val baxiTerm = baseBackAtk * p.baxiPlusGenericAtkPct

        // 6) 後段：對方減傷（體型 / 階級 / 攻擊屬性 / 怪物屬性）
        val defensiveBackMultiplier =
            (1.0 - p.targetSizeResistPct) *
                    (1.0 - p.targetClassResistPct) *
                    (1.0 - p.targetAttackElementResistPct) *
                    (1.0 - p.targetAttributeResistPct)

        // 7) 後段總 ATK
        val backAtk =
            (baseBackAtk * offensiveBackMultiplier + baxiTerm) *
                    defensiveBackMultiplier *
                    elementMod *                      // 屬性倍率
                    sizePenalty *                     // 體型修正
                    if (p.usingLeftHandWeapon) 0.75 else 1.0  // 左手武器 -25%

        // 8) 全段 ATK：前段 + 後段
        val baseFullAtk = p.frontAtk + backAtk

        // 9) P.ATK / 近遠增傷 / 爆傷 / 喵族 / 對方近遠減傷 / 種族減傷
        val rangeDamageMultiplier = onePlus(p.rangeDamagePct)
        val critDamageMultiplier = if (p.isCritical) onePlus(p.critDamagePct) else 1.0

        val fullAtk =
            baseFullAtk *
                    onePlus(p.pAtkPct) *
                    rangeDamageMultiplier *
                    critDamageMultiplier *
                    onePlus(p.vsDoramPct) *
                    (1.0 - p.targetRangeResistPct) *
                    (1.0 - p.targetRaceResistPctInFullAtk2)

        // 10) 技能倍率區
        val skillTotalRate = p.skillRate * onePlus(p.extraSkillRatePctFromBuff)
        val afterSkillRate =
            fullAtk *
                    skillTotalRate *
                    onePlus(p.highKatarTrainingPct)

        // 11) DEF 減傷
        val defMultiplier = calcDefMultiplier(
            res = p.res,
            backDef = p.backDef,
            frontDef = p.frontDef
        )
        val afterDef = afterSkillRate * defMultiplier

        // 12) 技能增傷卡 / 技能增傷技能
        val afterSkillDamageBonus =
            afterDef *
                    onePlus(p.skillDamagePctFromCards) *
                    onePlus(p.skillDamagePctFromSkills)

        // 13) 加上固定額外傷害（例如 靈氣劍）
        var final = afterSkillDamageBonus + p.fixedExtraDamage

        // 14) 最終乘區（潛擊 / 爪痕 / 紋章 / 金剛 / 俯身…）
        final *= product(p.finalMultipliers)

        // 15) 單 hit 取 floor，最後再乘 hitCount
        val perHit = floor(max(final, 0.0))
        return perHit * max(p.hitCount, 1)
    }

    /**
     * 計算武器 ATK（STR系武器版本）
     *
     * 公式：
     *   武器ATK(STR系) =
     *     ( ( weaponBaseAtk * (1 + STR/200 ± weaponLv*0.05)
     *         + refineAtk
     *         + strikeEnhance
     *       )
     *       * sizePenalty
     *     )
     *
     * 原文中「怒爆 / 致命塗毒 × 屬性表」比較麻煩，
     * 建議放到後段 or 由呼叫端自行處理，因此這裡不包含那段。
     *
     * @param varianceFactor 浮動值，0.0 代表取期望值，中間值約 = 0。要模擬最大傷害可填 +1.0，要最小傷害可填 -1.0。
     */
    fun calcStrWeaponAtk(
        weaponBaseAtk: Double,
        refineAtk: Double,
        strikeEnhance: Double,
        weaponLevel: Int,
        str: Int,
        sizePenalty: Double = 1.0,
        varianceFactor: Double = 0.0
    ): Double {
        val variance = weaponLevel * 0.05 * varianceFactor
        val baseTerm = weaponBaseAtk * (1.0 + str / 200.0 + variance)
        return (baseTerm + refineAtk + strikeEnhance) * sizePenalty
    }

    /**
     * 計算武器 ATK（DEX系武器版本：弓、槍械）
     *
     * 公式：
     *   武器ATK(DEX系) =
     *     ( ( weaponBaseAtk * (1 + DEX/200 ± weaponLv*0.05)
     *         + refineAtk
     *         + strikeEnhance
     *       ) * sizePenalty
     *       + ammoAtk
     *     )
     */
    fun calcDexWeaponAtk(
        weaponBaseAtk: Double,
        refineAtk: Double,
        strikeEnhance: Double,
        weaponLevel: Int,
        dex: Int,
        ammoAtk: Double,
        sizePenalty: Double = 1.0,
        varianceFactor: Double = 0.0
    ): Double {
        val variance = weaponLevel * 0.05 * varianceFactor
        val baseTerm = weaponBaseAtk * (1.0 + dex / 200.0 + variance)
        return (baseTerm + refineAtk + strikeEnhance) * sizePenalty + ammoAtk
    }

    /**
     * 依照 TWRO 公式計算 DEF 減傷乘數
     *
     * 原文：
     *   對方 ( ( (2000 + RES) ÷ (2000 + 5 × RES) )
     *        × ( (4000 + 後DEF) ÷ (4000 + 10 × 後DEF) )
     *        - 前DEF )
     *
     * 這裡假設 frontDef 已經是 0.0~1.0 的小數（若還是百分比請先 /100）。
     *
     * 回傳值為 0.0 ~ 1.0（乘在傷害上）
     */
    fun calcDefMultiplier(
        res: Double,
        backDef: Double,
        frontDef: Double
    ): Double {
        val resPart = (2000.0 + res) / (2000.0 + 5.0 * res)
        val backDefPart = (4000.0 + backDef) / (4000.0 + 10.0 * backDef)
        val raw = resPart * backDefPart - frontDef
        return raw.coerceIn(0.0, 1.0)
    }

    // ---------- 魔法傷害相關 ----------

    /**
     * 魔法傷害計算所需參數
     *
     * 仍然全部用「小數形式的百分比」，例如 30% = 0.3。
     */
    data class MagicParams(
        // --- MATK ---

        /** 前段 MATK（素質 MATK：BaseLv/4 + INT*1.5 + DEX/5 + LUK/3 + SPL*5） */
        val frontMatk: Double,

        /** 武器 MATK（武器基礎 MATK ± 浮動 + 精煉 MATK） */
        val weaponMatk: Double,

        /** 後段 MATK1（會被【魔力增幅】影響的那一段） */
        val backMatk1: Double = 0.0,

        /** 後段 MATK2（不受【魔力增幅】影響的那一段） */
        val backMatk2: Double = 0.0,

        /** 魔力增幅%（例如 魔力增幅 Lv10 = 50% → 0.5） */
        val magicAmplifyPct: Double = 0.0,

        // --- 目標屬性 / 體型 / 種族 / 階級 ---

        val attackElement: Element,
        val attackElementLevel: ElementLevel = ElementLevel.Lv1,
        val targetElement: Element,
        val targetElementLevel: ElementLevel = ElementLevel.Lv1,

        /** 對種族增傷% */
        val raceAtkPct: Double = 0.0,

        /** 對體型增傷% */
        val sizeAtkPct: Double = 0.0,

        /** 對階級增傷% */
        val classAtkPct: Double = 0.0,

        /** 對怪物屬性增傷% */
        val targetAttributeAtkPct: Double = 0.0,

        /** 特殊種族增傷% */
        val specialRaceAtkPct: Double = 0.0,

        /** SMATK%（特殊魔法攻擊力） */
        val sMatkPct: Double = 0.0,

        /** 對喵族增傷 10% → 0.1 */
        val vsDoramPct: Double = 0.0,

        /** 目標種族減傷% */
        val targetRaceResistPct: Double = 0.0,

        /** 目標體型減傷% */
        val targetSizeResistPct: Double = 0.0,

        /** 目標階級減傷% */
        val targetClassResistPct: Double = 0.0,

        /** 目標「怪物屬性」減傷% */
        val targetAttributeResistPct: Double = 0.0,

        // --- MATK% 與通用增傷 ---

        /** MATK%（對全 MATK 生效） */
        val matkPct: Double = 0.0,

        // --- 技能倍率與屬性魔法增傷 ---

        /** 技能倍率（例如 1200% → 12.0） */
        val skillRate: Double,

        /** 屬性魔法增傷%（例如「火屬性魔法傷害 +30%」→ 0.3） */
        val elementMagicDamagePct: Double = 0.0,

        /**
         * 攻擊屬性減傷%（含萬紫 / 毒弱化 / 彗星）
         * 這裡是「對方屬性耐性」那一段
         */
        val targetAttackElementResistPct: Double = 0.0,

        // --- MDEF / MRES 減傷 ---

        /** 對方 MRES */
        val mres: Double = 0.0,

        /** 對方前 MDEF（平減） */
        val frontMdef: Double = 0.0,

        /** 對方後 MDEF（裝備） */
        val backMdef: Double = 0.0,

        // --- 技能增傷（卡片 / 技能）---

        val skillDamagePctFromCards: Double = 0.0,
        val skillDamagePctFromSkills: Double = 0.0,

        // --- 最終乘區（潛擊 / 紋章 / 抵抗魔法 / 鋼筋 / 金剛 / 俯身…）---

        val finalMultipliers: List<Double> = emptyList(),

        /** Hit 數 */
        val hitCount: Int = 1
    )

    /**
     * 計算魔法傷害（單次施放總傷害，已乘 hitCount）
     */
    fun calcMagicDamage(p: MagicParams): Double {
        // 1) 元素相剋
        val elementMod = ElementModifier.getMultiplier(
            attack = p.attackElement,
            defense = p.targetElement,
            defenseLv = p.targetElementLevel
        )

        // 2) 全段 MATK
        val totalMatkBeforeRaceEtc =
            (p.frontMatk + p.weaponMatk + p.backMatk1) * onePlus(p.magicAmplifyPct) + p.backMatk2

        val totalMatk =
            totalMatkBeforeRaceEtc *
                    onePlus(p.matkPct) *
                    onePlus(p.raceAtkPct) *
                    onePlus(p.sizeAtkPct) *
                    onePlus(p.classAtkPct) *
                    onePlus(p.targetAttributeAtkPct) *
                    onePlus(p.specialRaceAtkPct) *
                    onePlus(p.sMatkPct) *
                    onePlus(p.vsDoramPct) *
                    (1.0 - p.targetRaceResistPct) *
                    (1.0 - p.targetSizeResistPct) *
                    (1.0 - p.targetClassResistPct) *
                    (1.0 - p.targetAttributeResistPct)

        // 3) 技能倍率、屬性倍率、屬性魔法增傷
        val afterSkillRate =
            totalMatk *
                    p.skillRate *
                    elementMod *
                    onePlus(p.elementMagicDamagePct) *
                    (1.0 - p.targetAttackElementResistPct)

        // 4) MDEF 減傷
        val mdefMultiplier = calcMdefMultiplier(
            mres = p.mres,
            backMdef = p.backMdef,
            frontMdef = p.frontMdef
        )
        val afterMdef = afterSkillRate * mdefMultiplier

        // 5) 技能增傷卡 / 技能增傷技能
        var final = afterMdef *
                onePlus(p.skillDamagePctFromCards) *
                onePlus(p.skillDamagePctFromSkills)

        // 6) 最終乘區
        final *= product(p.finalMultipliers)

        val perHit = floor(max(final, 0.0))
        return perHit * max(p.hitCount, 1)
    }

    /**
     * TWRO 的 MDEF 減傷乘數
     *
     * 原文：
     *   對方 ( ( (2000 + MRES) ÷ (2000 + 5 × MRES) )
     *        × ( (1000 + 後MDEF) ÷ (1000 + 10 × 後MDEF) )
     *        - 前MDEF )
     */
    fun calcMdefMultiplier(
        mres: Double,
        backMdef: Double,
        frontMdef: Double
    ): Double {
        val mresPart = (2000.0 + mres) / (2000.0 + 5.0 * mres)
        val backMdefPart = (1000.0 + backMdef) / (1000.0 + 10.0 * backMdef)
        val raw = mresPart * backMdefPart - frontMdef
        return raw.coerceIn(0.0, 1.0)
    }

    private fun sampleForPhysical(){
        val params = DamageCalculator.PhysicalParams(
            frontAtk = 1200.0,               // 素質ATK×2 + 修練ATK…你自己算好丟進來
            weaponAtk = 800.0,               // 可以用 calcStrWeaponAtk 算出來
            cardAtk = 150.0,
            weaponType = WeaponType.Katar,
            usingLeftHandWeapon = false,
            ignoreSizePenalty = false,
            attackRange = DamageCalculator.AttackRange.Melee,
            attackElement = Element.Neutral,
            targetElement = Element.Neutral,
            targetSize = MonsterSize.Medium,
            // 後段增傷
            raceAtkPct = 0.3,                // 對人形+30%
            sizeAtkPct = 0.0,
            classAtkPct = 0.0,
            // 全段
            pAtkPct = 0.2,                   // P.ATK+20%
            rangeDamagePct = 0.0,
            critDamagePct = 0.6,             // 如果是技能爆擊記得除2
            isCritical = true,
            // 技能倍率：假設十字斬首 Lv5 = 900%
            skillRate = 9.0,
            // DEF
            res = 0.0,
            frontDef = 0.2,
            backDef = 400.0,
            // hit 數
            hitCount = 1
        )
        val damage = DamageCalculator.calcPhysicalDamage(params)
    }

    private fun sampleForMagic() {
        val mParams = DamageCalculator.MagicParams(
            frontMatk = 1500.0,
            weaponMatk = 300.0,
            backMatk1 = 0.0,
            backMatk2 = 0.0,
            magicAmplifyPct = 0.5,          // 魔力增幅50%
            attackElement = Element.Fire,
            targetElement = Element.Earth,
            raceAtkPct = 0.3,               // 對地屬怪+30%
            matkPct = 0.2,                  // MATK+20%
            skillRate = 14.0,               // 1400%
            elementMagicDamagePct = 0.3,    // 火屬魔法+30%
            mres = 0.0,
            frontMdef = 0.1,
            backMdef = 200.0,
            hitCount = 7
        )

        val magicDamage = DamageCalculator.calcMagicDamage(mParams)
    }

    /**
     * 使用示範
     *
     * val result = SkillEngine.compute("soul_combo", ctx)
     *
     * // 取得該技能所有模式（通常只有一種）
     * val variant = result.variants.first()
     *
     * // 最終傷害公式輸出單一下
     * val singleHitDamage = calculateFinalDamage(variant.ratePerHit)
     *
     * // 若要算總傷害（例如 7 hit）
     * val totalDamage = singleHitDamage * variant.hits
     *
     */
}