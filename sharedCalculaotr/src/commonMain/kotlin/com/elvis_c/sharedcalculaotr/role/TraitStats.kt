package com.elvis_c.sharedcalculaotr.role

import kotlinx.serialization.Serializable

@Serializable
data class TraitStats(
    val pow: Int = 0, // 力量特性（增加 p.atk、物理攻擊力）
    val sta: Int = 0, // 體魄特性
    val wis: Int = 0, // 智慧特性
    val spl: Int = 0, // 咒術特性（增加 s.matk、魔法攻擊力）
    val con: Int = 0, // 集中特性（命中）
    val crt: Int = 0  // 暴擊特性（c.rate）
)
operator fun TraitStats.plus(other: TraitStats): TraitStats =
    TraitStats(
        pow = pow + other.pow,
        sta = sta + other.sta,
        wis = wis + other.wis,
        spl = spl + other.spl,
        con = con + other.con,
        crt = crt + other.crt
    )