package com.elvis_c.sharedcalculaotr.engine.skill

import com.elvis_c.sharedcalculaotr.engine.Element

/**
 * 一個技能的某一種模式（variant）
 * 例如：一般 / 結霜、總和 / 爆炸、火屬段 / 暗屬段等
 */
data class SkillVariantResult(
    val key: String,               // 用於邏輯識別 (e.g., "normal", "frost", "total", "explosion", "fire", "dark")
    val label: String,             // UI 顯示用 (e.g., "一般傷害", "結霜傷害")
    val element: Element?,         // 此傷害段使用的攻擊屬性（可能是 null）
    val ratePerHit: Double,        // 單一下傷害倍率（例如 4200% → 42.0）
    val hits: Int                  // 該模式的 hit 數（例如靈魂連擊＝SLV+2）
)
