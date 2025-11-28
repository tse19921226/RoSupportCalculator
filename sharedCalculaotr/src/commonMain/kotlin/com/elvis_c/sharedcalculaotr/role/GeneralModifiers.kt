package com.elvis_c.sharedcalculaotr.role

import kotlinx.serialization.Serializable

@Serializable
data class GeneralModifiers(
    val skillDamagePercent: Double = 0.0, // 技能增傷%

    val skillDelayReducePercent: Double = 0.0,     // 技能後延遲減少%
    val skillIndepDelayReducePercent: Double = 0.0, // 技能獨立延遲減少%

    val variableCastReducePercent: Double = 0.0, // 變動詠唱減少%
    val variableCastReduceSec: Double = 0.0,     // 變動詠唱減少秒數

    val fixedCastReducePercent: Double = 0.0, // 固定詠唱減少%
    val fixedCastReduceSec: Double = 0.0,     // 固定詠唱減少秒數

    val aspd: Double = 0.0,                       // ASPD（攻擊速度）
    val afterAttackDelayReducePercent: Double = 0.0, // 攻擊後延遲減少%

    val critRate: Double = 0.0,          // 爆擊率（裝備額外給的）
    val elementResReducePercent: Double = 0.0, // 屬性耐性減少%

    val bonusStats: BaseStats = BaseStats(),      // 裝備增加的六大素質
    val bonusTraitStats: TraitStats = TraitStats(), // 裝備增加的六大特性素質

    val hpPercent: Double = 0.0,     // HP%
    val hp: Int = 0,                 // HP（固定值）

    val spPercent: Double = 0.0,     // SP%
    val sp: Int = 0                  // SP（固定值）

)
