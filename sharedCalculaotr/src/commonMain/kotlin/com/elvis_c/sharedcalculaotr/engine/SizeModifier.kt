package com.elvis_c.sharedcalculaotr.engine

import com.elvis_c.sharedcalculaotr.equipment.model.WeaponType

/**
 * 武器種類 vs 體型 的傷害修正表（物理傷害限定）
 *
 * 支援特殊效果：
 *   - ignorePenalty = true → 回傳 1.0（100% 完整傷害）
 */
object SizeModifier {

    /** 100 → 1.0 倍 */
    private fun pct(v: Int) = v / 100.0

    /**
     * 主資料表
     * Map: WeaponType -> (MonsterSize -> 倍率)
     */
    private val table: Map<WeaponType, Map<MonsterSize, Double>> =
        mapOf(
            WeaponType.None to mapOf(
                MonsterSize.Small to pct(100),
                MonsterSize.Medium to pct(100),
                MonsterSize.Large to pct(100)
            ),

            WeaponType.Dagger to mapOf(
                MonsterSize.Small to pct(100),
                MonsterSize.Medium to pct(75),
                MonsterSize.Large to pct(50)
            ),

            WeaponType.OneHandSword to mapOf(
                MonsterSize.Small to pct(75),
                MonsterSize.Medium to pct(100),
                MonsterSize.Large to pct(75)
            ),

            WeaponType.TwoHandSword to mapOf(
                MonsterSize.Small to pct(75),
                MonsterSize.Medium to pct(75),
                MonsterSize.Large to pct(100)
            ),

            WeaponType.OneHandAxe to mapOf(
                MonsterSize.Small to pct(75),
                MonsterSize.Medium to pct(75),
                MonsterSize.Large to pct(100)
            ),

            WeaponType.TwoHandAxe to mapOf(
                MonsterSize.Small to pct(75),
                MonsterSize.Medium to pct(75),
                MonsterSize.Large to pct(100)
            ),

            WeaponType.Mace to mapOf(
                MonsterSize.Small to pct(75),
                MonsterSize.Medium to pct(100),
                MonsterSize.Large to pct(100)
            ),

            WeaponType.OneHandSpear to mapOf(
                MonsterSize.Small to pct(75),
                MonsterSize.Medium to pct(100),
                MonsterSize.Large to pct(100)
            ),

            WeaponType.TwoHandSpear to mapOf(
                MonsterSize.Small to pct(75),
                MonsterSize.Medium to pct(100),
                MonsterSize.Large to pct(100)
            ),

            WeaponType.OneHandStaff to mapOf(
                MonsterSize.Small to pct(100),
                MonsterSize.Medium to pct(100),
                MonsterSize.Large to pct(100)
            ),

            WeaponType.TwoHandStaff to mapOf(
                MonsterSize.Small to pct(100),
                MonsterSize.Medium to pct(100),
                MonsterSize.Large to pct(100)
            ),

            WeaponType.Bow to mapOf(
                MonsterSize.Small to pct(100),
                MonsterSize.Medium to pct(100),
                MonsterSize.Large to pct(75)
            ),

            WeaponType.Knuckle to mapOf(
                MonsterSize.Small to pct(75),
                MonsterSize.Medium to pct(100),
                MonsterSize.Large to pct(75)
            ),

            WeaponType.Katar to mapOf(
                MonsterSize.Small to pct(100),
                MonsterSize.Medium to pct(100),
                MonsterSize.Large to pct(75)
            ),

            WeaponType.Book to mapOf(
                MonsterSize.Small to pct(100),
                MonsterSize.Medium to pct(100),
                MonsterSize.Large to pct(50)
            ),

            WeaponType.Whip to mapOf(
                MonsterSize.Small to pct(75),
                MonsterSize.Medium to pct(100),
                MonsterSize.Large to pct(75)
            ),

            WeaponType.Musical to mapOf(
                MonsterSize.Small to pct(75),
                MonsterSize.Medium to pct(100),
                MonsterSize.Large to pct(75)
            ),

            WeaponType.Revolver to mapOf(
                MonsterSize.Small to pct(100),
                MonsterSize.Medium to pct(100),
                MonsterSize.Large to pct(100)
            ),

            WeaponType.Rifle to mapOf(
                MonsterSize.Small to pct(100),
                MonsterSize.Medium to pct(100),
                MonsterSize.Large to pct(100)
            ),

            WeaponType.Gatling to mapOf(
                MonsterSize.Small to pct(100),
                MonsterSize.Medium to pct(100),
                MonsterSize.Large to pct(100)
            ),

            WeaponType.Shotgun to mapOf(
                MonsterSize.Small to pct(75),
                MonsterSize.Medium to pct(100),
                MonsterSize.Large to pct(75)
            ),

            WeaponType.GrenadeLauncher to mapOf(
                MonsterSize.Small to pct(100),
                MonsterSize.Medium to pct(100),
                MonsterSize.Large to pct(100)
            ),

            WeaponType.Huuma to mapOf(
                MonsterSize.Small to pct(75),
                MonsterSize.Medium to pct(75),
                MonsterSize.Large to pct(100)
            )
        )

    /**
     * 取得傷害倍率（物理限定）
     *
     * @param weapon 武器種類
     * @param size 怪物體型
     * @param ignorePenalty 若裝備/卡片有「無視體型懲罰」則回傳 1.0
     */
    fun getMultiplier(
        weapon: WeaponType,
        size: MonsterSize,
        ignorePenalty: Boolean = false
    ): Double {

        if (ignorePenalty) return 1.0

        val row = table[weapon] ?: return 1.0
        return row[size] ?: 1.0
    }

    private fun sample() {
        val weapon = WeaponType.OneHandSword
        val targetSize = MonsterSize.Large

        val ignore = false// 無極效果，來自裝備條件、卡片或技能

        val sizeMod = SizeModifier.getMultiplier(
            weapon = weapon,
            size = targetSize,
            ignorePenalty = ignore
        )
    }
}