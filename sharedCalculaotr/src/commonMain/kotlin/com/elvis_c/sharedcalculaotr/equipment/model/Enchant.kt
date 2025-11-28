package com.elvis_c.sharedcalculaotr.equipment.model

import com.elvis_c.sharedcalculaotr.equipment.effect.EffectNode
import kotlinx.serialization.Serializable

@Serializable
data class Enchant(
    val id: String,                  // enchant unique id，例如 magic_nebula_lv3
    val name: String,                // 顯示用名稱
    val group: String,               // 附魔系列：SorceryCluster / MagicNebula / MagicNiv / AutumnLife
    val slotIndex: Int,              // 附在哪一洞（2, 3, 4)
    val level: Int? = null,          // 若是 Lv1~3 / Lv1~20 的等級（非等級附魔可為 null）
    val effects: List<EffectNode>,   // 這個附魔所有效果（條件、百分比、固定值都在裡面）
    val description: String? = null  // 原始敘述（顯示／備註用）
)