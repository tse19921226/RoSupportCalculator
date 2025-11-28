package com.elvis_c.sharedcalculaotr.role

import kotlinx.serialization.Serializable

@Serializable
data class DerivedStats(
    val baseAtk: Int = 0,   // 前段 ATK（素質提供的攻擊力）
    val equipAtk: Int = 0,  // 後段 ATK（裝備提供的攻擊力）

    val baseMatk: Int = 0,  // 前段 MATK（素質提供的魔攻）
    val equipMatk: Int = 0, // 後段 MATK（裝備提供的魔攻）

    val pAtk: Double = 0.0, // p.atk（特性 POW 轉換的物攻補正）
    val sMatk: Double = 0.0 // s.matk（特性 SPL 轉換的魔攻補正）
)
