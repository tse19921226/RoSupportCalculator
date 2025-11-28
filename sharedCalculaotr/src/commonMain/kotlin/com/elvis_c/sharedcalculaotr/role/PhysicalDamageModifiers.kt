package com.elvis_c.sharedcalculaotr.role

import kotlinx.serialization.Serializable

@Serializable

data class PhysicalDamageModifiers(
    val pAtk: Double = 0.0,           // p.atk（物理特性攻擊補正）
    val atk: Int = 0,                 // 裝備提供的 ATK
    val atkPercent: Double = 0.0,     // ATK%（物攻百分比加成）

    val raceDamage: Double = 0.0,         // 對種族增傷%
    val sizeDamage: Double = 0.0,         // 對體型增傷%
    val classDamage: Double = 0.0,        // 對階級增傷%
    val elementMonsterDamage: Double = 0.0, // 對屬性魔物增傷%
    val specificMonsterDamage: Double = 0.0, // 對特定魔物增傷%

    val critDamage: Double = 0.0,     // 爆擊傷害%
    val ignoreDef: Double = 0.0,      // 無視防禦%
    val ignorePhyTraitDef: Double = 0.0, // 無視物理特性防禦%

    val critRate: Double = 0.0,       // 爆擊率%
    val meleeDamage: Double = 0.0,    // 近距離物理傷害%
    val rangedDamage: Double = 0.0    // 遠距離物理傷害%
)
