package com.elvis_c.sharedcalculaotr.engine.skill

import kotlinx.serialization.Serializable

/**
 * 技能的基礎公式描述。
 *
 * damageExpression 使用文字描述（之後 SkillEngine 會解析或直接以程式撰寫）
 * hitCount 支援「負數 = 多目標分散HIT」等特殊情況
 */
@Serializable
data class SkillFormula(
    val damageExpression: String, // e.g. "(SLV*300 + SPL*3) * BLV / 100"
    val hitCountExpression: String, // e.g. "SLV + 2"
    val fixedCast: Double,     // 固定詠唱 (sec)
    val variableCast: Double,  // 可變詠唱 (sec)
    val independentDelay: Double, // 獨立延遲 (sec)
    val globalDelay: Double       // 共通後延遲 (sec)
)