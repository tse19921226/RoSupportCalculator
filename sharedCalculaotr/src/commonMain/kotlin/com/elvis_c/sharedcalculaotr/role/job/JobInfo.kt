package com.elvis_c.sharedcalculaotr.role.job

import com.elvis_c.sharedcalculaotr.role.BaseStats
import com.elvis_c.sharedcalculaotr.role.TraitStats
import kotlinx.serialization.Serializable

@Serializable
data class JobInfo(
    val branch: JobBranch,

    // 此職業分支的所有名稱（四轉 / 三轉 / 二轉）
    val displayNames: String,

    // 滿 Job（四轉 60 / 三轉 70）後的素質點加成
    val baseStatBonus: BaseStats = BaseStats(),

    // 滿 Job 後的特性素質加成（POW / STA / WIS / SPL / CON / CRT）
    val traitStatBonus: TraitStats = TraitStats()
)