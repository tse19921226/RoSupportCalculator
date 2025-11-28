package com.elvis_c.sharedcalculaotr.equipment.effect

import kotlinx.serialization.Serializable

@Serializable
data class Scaling(
    val perUnit: Double,  // 每 1 素質加多少
    val source: StatType  // 來源素質（STR / SPL…）
)
