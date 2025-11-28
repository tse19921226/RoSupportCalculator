package com.elvis_c.sharedcalculaotr.equipment.effect

import com.elvis_c.sharedcalculaotr.equipment.trigger.Trigger
import kotlinx.serialization.Serializable

@Serializable
data class EffectNode(
    val target: EffectTarget,     // 作用目標（ATK? MATK? 種族傷? 技能?）
    val value: Double,            // 基礎數值（固定加成 / 百分比）
    val type: EffectType,         // 加成方式（固定、%、乘算）
    val trigger: Trigger? = null, // 要不要滿足條件才生效（精煉、階級、素質…）
    val scaling: Scaling? = null  // 是否依照 SPL/STR/DEX/Refine 成長

)
