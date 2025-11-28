package com.elvis_c.sharedcalculaotr.role

import kotlinx.serialization.Serializable

@Serializable
data class BaseStats(
    val str: Int = 1, // 力量（增加物理攻擊、負重、部分技能）
    val agi: Int = 1, // 敏捷（攻速 ASPD、迴避、移動速度）
    val vit: Int = 1, // 體質（HP、防禦、恢復能力）
    val int: Int = 1, // 智力（魔攻 MATK、詠唱、SP）
    val dex: Int = 1, // 靈巧（命中、固定詠唱、遠距增傷）
    val luk: Int = 1  // 幸運（爆擊率、爆傷、異常抗性）
)

operator fun BaseStats.plus(other: BaseStats): BaseStats =
    BaseStats(
        str = str + other.str,
        agi = agi + other.agi,
        vit = vit + other.vit,
        int = int + other.int,
        dex = dex + other.dex,
        luk = luk + other.luk
    )
