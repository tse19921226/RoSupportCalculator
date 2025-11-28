package com.elvis_c.sharedcalculaotr.equipment.model

import com.elvis_c.sharedcalculaotr.equipment.effect.EffectNode
import com.elvis_c.sharedcalculaotr.equipment.model.EquipmentCategory
import kotlinx.serialization.Serializable

@Serializable
data class Equipment(
    val id: String,
    val name: String,
    val category: EquipmentCategory,   // 武器、鎧甲、鞋子…
    val weaponType: WeaponType? = null,
    val armorLevel: Int? = null,
    val baseAtk: Int = 0,
    val baseMatk: Int = 0,
    val weight: Int = 0,
    val refineLevel: Int = 0,
    val grade: Grade = Grade.N,
    val effects: List<EffectNode> = emptyList(),
    val cards: List<Card> = emptyList(),
    val enchants: List<Enchant> = emptyList()
)
