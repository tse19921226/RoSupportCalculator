package com.elvis_c.sharedcalculaotr.equipment.model

import com.elvis_c.sharedcalculaotr.equipment.effect.EffectNode
import kotlinx.serialization.Serializable

@Serializable
data class Card(
    val id: String,
    val name: String,
    val effects: List<EffectNode>
)