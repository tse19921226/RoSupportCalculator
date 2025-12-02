package com.elvis_c.sharedcalculaotr.engine.skill

import kotlinx.serialization.Serializable
import com.elvis_c.sharedcalculaotr.role.job.JobBranch

@Serializable
data class Skill(
    val id: String,
    val name: String,
    val job: JobBranch,
    val maxLevel: Int,

    // 技能公式
    val formula: SkillFormula,

    // 技能是否屬於魔法（影響 DamageCalculator 走哪個路徑）
    val isMagic: Boolean = true,

    // 支援爆擊
    val canCritical: Boolean = false
)