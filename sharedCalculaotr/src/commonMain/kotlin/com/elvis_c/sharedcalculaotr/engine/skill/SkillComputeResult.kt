package com.elvis_c.sharedcalculaotr.engine.skill


/**
 * 一個技能的多種模式結果集合
 */
data class SkillComputeResult(
    val variants: List<SkillVariantResult>
)