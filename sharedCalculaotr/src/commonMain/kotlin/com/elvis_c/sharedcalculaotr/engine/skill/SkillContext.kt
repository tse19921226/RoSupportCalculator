package com.elvis_c.sharedcalculaotr.engine.skill

data class SkillContext(
    val slv: Int,   // Skill Level
    val blv: Int,   // Base Level

    // 基本屬性
    val str: Int,
    val agi: Int,
    val vit: Int,
    val int: Int,
    val dex: Int,
    val luk: Int,

    // 特性素質（四轉）
    val pow: Int,
    val sta: Int,
    val wis: Int,
    val spl: Int,
    val con: Int,
    val crt: Int,

    // 技能判斷需要的額外狀態（可隨時擴充）
    // 結霜
    val targetFrozen: Boolean = false,
)
