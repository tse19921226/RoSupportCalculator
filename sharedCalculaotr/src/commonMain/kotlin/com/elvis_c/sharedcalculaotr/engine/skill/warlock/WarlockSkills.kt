package com.elvis_c.sharedcalculaotr.engine.skill.warlock

import com.elvis_c.sharedcalculaotr.engine.skill.Skill
import com.elvis_c.sharedcalculaotr.engine.skill.SkillFormula
import com.elvis_c.sharedcalculaotr.engine.skill.SkillRepository
import com.elvis_c.sharedcalculaotr.role.job.JobBranch

object WarlockSkills {

    // --- 靈魂連擊 ---
    val SoulCombo = Skill(
        id = "soul_combo",
        name = "靈魂連擊",
        job = JobBranch.Warlock,
        maxLevel = 5,
        isMagic = true,
        canCritical = false,
        formula = SkillFormula(
            damageExpression = "(SLV * 300 + SPL * 3) * BLV / 100",
            hitCountExpression = "SLV + 2",
            fixedCast = 1.0,
            variableCast = 1.0,
            independentDelay = 0.7,
            globalDelay = 0.0
        )
    )

    // --- 靈魂爆炸 ---
    val SoulExplosion = Skill(
        id = "soul_explosion",
        name = "靈魂爆炸",
        job = JobBranch.Warlock,
        maxLevel = 5,
        isMagic = true,
        canCritical = false,
        formula = SkillFormula(
            damageExpression = "(1000 + SLV * 200 + INT) * BLV / 100",
            hitCountExpression = "2",
            fixedCast = 0.0,
            variableCast = 2.0,
            independentDelay = 0.0,
            globalDelay = 0.5
        )
    )

    // --- 碧血隕石 ---
    val BloodMeteor = Skill(
        id = "blood_meteor",
        name = "碧血隕石",
        job = JobBranch.Warlock,
        maxLevel = 5,
        isMagic = true,
        canCritical = false,
        formula = SkillFormula(
            damageExpression = "(700 + SLV * 600) * BLV / 100",
            hitCountExpression = "7", // -7HIT → 7
            fixedCast = 1.0,
            variableCast = 5.0,
            independentDelay = 5.0,
            globalDelay = 0.5
        )
    )

    // --- 地牛翻身 ---
    val EarthShock = Skill(
        id = "earth_shock",
        name = "地牛翻身",
        job = JobBranch.Warlock,
        maxLevel = 5,
        isMagic = true,
        canCritical = false,
        formula = SkillFormula(
            damageExpression = "(1000 + SLV * 600) * BLV / 100",
            hitCountExpression = "10", // -10HIT → 10
            fixedCast = 1.0,
            variableCast = 6.0,
            independentDelay = 7.0,
            globalDelay = 0.5
        )
    )

    // --- 凍僵術 ---
    val FrostBite = Skill(
        id = "frost_bite",
        name = "凍僵術",
        job = JobBranch.Warlock,
        maxLevel = 5,
        isMagic = true,
        canCritical = false,
        formula = SkillFormula(
            damageExpression = "(1000 + SLV * 300) * BLV / 100",
            hitCountExpression = "5", // -5 HIT → 5 hit
            fixedCast = 1.0,
            variableCast = 4.0,
            independentDelay = 4.0,
            globalDelay = 0.5
        )
    )

    // --- 凍僵術 ---
    val FrostBiteFrozen = Skill(
        id = "frost_bite_frozen",
        name = "凍僵術（結霜）",
        job = JobBranch.Warlock,
        maxLevel = 5,
        isMagic = true,
        canCritical = false,
        formula = SkillFormula(
            damageExpression = "(1200 + SLV * 600) * BLV / 100",
            hitCountExpression = "5",
            fixedCast = 1.0,
            variableCast = 4.0,
            independentDelay = 4.0,
            globalDelay = 0.5
        )
    )

    // --- 連鎖電擊 ---
    val ChainLightning = Skill(
        id = "chain_lightning",
        name = "連鎖電擊",
        job = JobBranch.Warlock,
        maxLevel = 5,
        isMagic = true,
        canCritical = false,
        formula = SkillFormula(
            damageExpression = "(500 + SLV * 100) * BLV / 100 + 800",
            hitCountExpression = "1",
            fixedCast = 1.0,
            variableCast = 5.5,
            independentDelay = 0.0,
            globalDelay = 3.0
        )
    )

    // --- 地獄火焰 ---
    val HellFire = Skill(
        id = "hell_fire",
        name = "地獄火焰",
        job = JobBranch.Warlock,
        maxLevel = 5,
        isMagic = true,
        canCritical = false,
        formula = SkillFormula(
            damageExpression = "dual-element: fire + dark",
            hitCountExpression = "multi",
            fixedCast = 1.0,
            variableCast = 3.0,
            independentDelay = 3.0,
            globalDelay = 0.5
        )
    )

    // --- 毀滅慧星 ---
    val DoomsdayMeteor = Skill(
        id = "doomsday_meteor",
        name = "毀滅慧星",
        job = JobBranch.Warlock,
        maxLevel = 5,
        isMagic = true,
        canCritical = false,
        formula = SkillFormula(
            damageExpression = "(3000 + SLV * 600) * BLV / 100",
            hitCountExpression = "10", // -10 HIT → 10
            fixedCast = 2.0,
            variableCast = 10.0,
            independentDelay = 20.0,
            globalDelay = 1.5
        )
    )

    // --- 焰火之徑 ---
    val FlameRoad = Skill(
        id = "flame_road",
        name = "焰火之徑",
        job = JobBranch.Warlock,
        maxLevel = 5,
        isMagic = true,
        canCritical = false,
        formula = SkillFormula(
            damageExpression = "(50 + SLV * 740 + SPL * 5) * BLV / 100",
            hitCountExpression = "5",
            fixedCast = 0.0,
            variableCast = 0.0,
            independentDelay = 5.0,
            globalDelay = 0.75
        )
    )

    // --- 豔紅魔矢（主技能） ---
    val CrimsonArrow = Skill(
        id = "crimson_arrow",
        name = "豔紅魔矢",
        job = JobBranch.Warlock,
        maxLevel = 5,
        isMagic = true,
        canCritical = false,
        formula = SkillFormula(
            damageExpression = "(SLV*400+SPL*3)*BLV/100 + (SLV*750+SPL*5)*BLV/100  // total version",
            hitCountExpression = "multi",  // 多段 → compute 處理
            fixedCast = 1.5,
            variableCast = 0.0,
            independentDelay = 0.3,
            globalDelay = 0.5
        )
    )

    // --- 萬紫千紅 ---
    val ThousandThorns = Skill(
        id = "thousand_thorns",
        name = "萬紫千紅",
        job = JobBranch.Warlock,
        maxLevel = 5,
        isMagic = true,
        canCritical = false,
        formula = SkillFormula(
            damageExpression = "multi: base / peak5",
            hitCountExpression = "SLV * 2",
            fixedCast = 0.0,
            variableCast = 0.0,
            independentDelay = 6.0,
            globalDelay = 0.5
        )
    )

    // --- 冰晶飛瀑 ---
    val IceCascade = Skill(
        id = "ice_cascade",
        name = "冰晶飛瀑",
        job = JobBranch.Warlock,
        maxLevel = 5,
        isMagic = true,
        canCritical = false,
        formula = SkillFormula(
            damageExpression = "(180 + SLV * 760 + SPL * 5) * BLV / 100",
            hitCountExpression = "8",
            fixedCast = 0.0,
            variableCast = 0.0,
            independentDelay = 5.0,
            globalDelay = 0.75
        )
    )

    // --- 冰刃斬 ---
    val IceSlash = Skill(
        id = "ice_slash",
        name = "冰刃斬",
        job = JobBranch.Warlock,
        maxLevel = 5,
        isMagic = true,
        canCritical = false,
        formula = SkillFormula(
            damageExpression = "peak: normal / peak",
            hitCountExpression = "3",
            fixedCast = 0.0,
            variableCast = 0.0,
            independentDelay = 0.45,
            globalDelay = 0.5
        )
    )

    // --- 水晶波爆 ---
    val CrystalBurst = Skill(
        id = "crystal_burst",
        name = "水晶波爆",
        job = JobBranch.Warlock,
        maxLevel = 5,
        isMagic = true,
        canCritical = false,
        formula = SkillFormula(
            damageExpression = "peak: normal / peak2",
            hitCountExpression = "multi",
            fixedCast = 0.0,
            variableCast = 0.0,
            independentDelay = 2.0,
            globalDelay = 0.5
        )
    )

    // --- 龍捲風暴 ---
    val TornadoStorm = Skill(
        id = "tornado_storm",
        name = "龍捲風暴",
        job = JobBranch.Warlock,
        maxLevel = 5,
        isMagic = true,
        canCritical = false,
        formula = SkillFormula(
            damageExpression = "(100 + SLV * 760 + SPL * 5) * BLV / 100",
            hitCountExpression = "10",
            fixedCast = 0.0,
            variableCast = 0.0,
            independentDelay = 5.0,
            globalDelay = 0.75
        )
    )

    // --- 暴風加農砲 ---
    val StormCannon = Skill(
        id = "storm_cannon",
        name = "暴風加農砲",
        job = JobBranch.Warlock,
        maxLevel = 5,
        isMagic = true,
        canCritical = false,
        formula = SkillFormula(
            damageExpression = "peak: normal / peak",
            hitCountExpression = "2", // -2 HIT → 2 hits
            fixedCast = 1.5,
            variableCast = 0.0,
            independentDelay = 0.3,
            globalDelay = 0.5
        )
    )

    // --- 毀滅颶風 ---
    val DestructionGale = Skill(
        id = "destruction_gale",
        name = "毀滅颶風",
        job = JobBranch.Warlock,
        maxLevel = 5,
        isMagic = true,
        canCritical = false,
        formula = SkillFormula(
            damageExpression = "peak: normal / peak1",
            hitCountExpression = "10",
            fixedCast = 0.0,
            variableCast = 0.0,
            independentDelay = 2.5,
            globalDelay = 0.5
        )
    )

    // --- 地層震動 ---
    val SeismicWave = Skill(
        id = "seismic_wave",
        name = "地層震動",
        job = JobBranch.Warlock,
        maxLevel = 5,
        isMagic = true,
        canCritical = false,
        formula = SkillFormula(
            damageExpression = "(100 + SLV * 730 + SPL * 5) * BLV / 100",
            hitCountExpression = "5",
            fixedCast = 0.0,
            variableCast = 0.0,
            independentDelay = 5.0,
            globalDelay = 0.75
        )
    )

    // --- 巨石降臨 ---
    val MeteoriteFall = Skill(
        id = "meteorite_fall",
        name = "巨石降臨",
        job = JobBranch.Warlock,
        maxLevel = 5,
        isMagic = true,
        canCritical = false,
        formula = SkillFormula(
            damageExpression = "peak: normal / peak",
            hitCountExpression = "5",
            fixedCast = 0.0,
            variableCast = 0.0,
            independentDelay = 0.3,
            globalDelay = 0.5
        )
    )

    // --- 震裂術 ---
    val Rupture = Skill(
        id = "rupture",
        name = "震裂術",
        job = JobBranch.Warlock,
        maxLevel = 5,
        isMagic = true,
        canCritical = false,
        formula = SkillFormula(
            damageExpression = "(SLV * 950 + SPL * 5) * BLV / 100",
            hitCountExpression = "SLV * 4 / SLV * 8",  // 一般 / 巔峰1
            fixedCast = 0.0,
            variableCast = 0.0,
            independentDelay = 6.0,
            globalDelay = 1.0
        )
    )

    // --- 致命放射 ---
    val FatalRay = Skill(
        id = "fatal_ray",
        name = "致命放射",
        job = JobBranch.Warlock,
        maxLevel = 5,
        isMagic = true,
        canCritical = false,
        formula = SkillFormula(
            damageExpression = "(SLV * 2800 + SPL * 5) * BLV / 100",
            hitCountExpression = "1",
            fixedCast = 0.0,
            variableCast = 0.0,
            independentDelay = 0.0,
            globalDelay = 0.0
        )
    )

    // --- 神秘幻滅 ---
    val MysticRuin = Skill(
        id = "mystic_ruin",
        name = "神秘幻滅",
        job = JobBranch.Warlock,
        maxLevel = 5,
        isMagic = true,
        canCritical = false,
        formula = SkillFormula(
            damageExpression = "(SLV * 950 + SPL * 5) * BLV / 100",
            hitCountExpression = "14",
            fixedCast = 0.0,
            variableCast = 0.0,
            independentDelay = 4.0,
            globalDelay = 0.75
        )
    )

    // --- 星際突襲 ---
    val StarAssault = Skill(
        id = "star_assault",
        name = "星際突襲",
        job = JobBranch.Warlock,
        maxLevel = 10,
        isMagic = true,
        canCritical = false,
        formula = SkillFormula(
            damageExpression = "multi: normal / normal_total / undead_dragon / undead_dragon_total",
            hitCountExpression = "1",
            fixedCast = 0.0,
            variableCast = 0.0,
            independentDelay = 6.0,
            globalDelay = 1.0
        )
    )

    fun registerAll() {
        SkillRepository.register(SoulCombo)
        SkillRepository.register(SoulExplosion)
        SkillRepository.register(BloodMeteor)
        SkillRepository.register(EarthShock)
        SkillRepository.register(FrostBite)
        SkillRepository.register(FrostBiteFrozen)
        SkillRepository.register(ChainLightning)
        SkillRepository.register(HellFire)
        SkillRepository.register(DoomsdayMeteor)
        SkillRepository.register(FlameRoad)
        SkillRepository.register(CrimsonArrow)
        SkillRepository.register(ThousandThorns)
        SkillRepository.register(IceCascade)
        SkillRepository.register(IceSlash)
        SkillRepository.register(CrystalBurst)
        SkillRepository.register(TornadoStorm)
        SkillRepository.register(StormCannon)
        SkillRepository.register(DestructionGale)
        SkillRepository.register(SeismicWave)
        SkillRepository.register(MeteoriteFall)
        SkillRepository.register(Rupture)
        SkillRepository.register(FatalRay)
        SkillRepository.register(MysticRuin)
        SkillRepository.register(StarAssault)
    }
}