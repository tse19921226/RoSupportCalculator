package com.elvis_c.sharedcalculaotr.engine.skill

/**
 * 各技能的倍率、HIT 計算邏輯
 */
data class SkillComputeDefinition(
    val skillId: String,
    val compute: (SkillContext) -> SkillComputeResult
)