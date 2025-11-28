package com.elvis_c.sharedcalculaotr.role

import kotlinx.serialization.Serializable

@Serializable
data class MagicDamageModifiers(
    val sMatk: Double = 0.0,          // s.matk（魔法特性攻擊）
    val matk: Int = 0,                // 裝備提供的 MATK
    val matkPercent: Double = 0.0,    // MATK%（魔攻百分比）

    val raceDamage: Double = 0.0,         // 對種族增傷%
    val sizeDamage: Double = 0.0,         // 對體型增傷%
    val classDamage: Double = 0.0,        // 對階級增傷%
    val elementMonsterDamage: Double = 0.0, // 對屬性魔物增傷%
    val specificMonsterDamage: Double = 0.0, // 對特定魔物增傷%

    val elementMagicDamage: Double = 0.0, // 屬性魔法增傷%

    val ignoreDef: Double = 0.0,        // 無視魔防%
    val ignoreMagTraitDef: Double = 0.0  // 無視魔法特性防禦%
)
