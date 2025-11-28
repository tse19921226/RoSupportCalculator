package com.elvis_c.sharedcalculaotr.engine

import kotlinx.serialization.Serializable

@Serializable
enum class Element {
    Neutral,   // 無
    Fire,      // 火
    Water,     // 水
    Wind,      // 風
    Earth,     // 地
    Poison,    // 毒
    Holy,      // 聖
    Dark,      // 暗
    Ghost,     // 念
    Undead     // 不死
}