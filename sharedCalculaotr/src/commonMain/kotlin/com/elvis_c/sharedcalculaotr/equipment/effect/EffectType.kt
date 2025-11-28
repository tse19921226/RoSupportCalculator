package com.elvis_c.sharedcalculaotr.equipment.effect

import kotlinx.serialization.Serializable

@Serializable
enum class EffectType {
    Flat,      // 固定值 +50 MATK
    Percent,   // 百分比加成 +15%
    Multiply   // 乘算倍率 ×1.2
}