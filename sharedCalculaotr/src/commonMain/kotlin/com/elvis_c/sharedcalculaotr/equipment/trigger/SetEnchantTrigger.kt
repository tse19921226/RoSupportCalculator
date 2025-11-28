package com.elvis_c.sharedcalculaotr.equipment.trigger

import com.elvis_c.sharedcalculaotr.equipment.model.Grade
import kotlinx.serialization.Serializable

@Serializable
sealed interface Trigger

@Serializable
data class RefineTrigger(
    val every: Int,     // 每 +2 / +3
    val minRefine: Int = 0 // 例如 +11以上
) : Trigger

@Serializable
data class GradeTrigger(
    val grade: Grade    // N / D / C / B / A
) : Trigger

@Serializable
data class StatTrigger(
    val stat: StatType, // SPL / STR / VIT
    val every: Int      // 每 +15 SPL
) : Trigger

@Serializable
data class SetCardTrigger(   // 與卡片套裝效果
    val cardId: String
) : Trigger

@Serializable
data class SetEnchantTrigger(
    val enchantId: String
) : Trigger