package com.elvis_c.sharedcalculaotr.role

import com.elvis_c.sharedcalculaotr.role.job.JobBranch
import kotlinx.serialization.Serializable

@Serializable
data class CharacterStatus(
    val baseStats: BaseStats = BaseStats(),          // 六大素質
    val traitStats: TraitStats = TraitStats(),        // 六大特性素質

    val derivedStats: DerivedStats = DerivedStats(),  // 前後段 atk/matk、p.atk、s.matk

    val physical: PhysicalDamageModifiers = PhysicalDamageModifiers(), // 物理加成
    val magical: MagicDamageModifiers = MagicDamageModifiers(),         // 魔法加成
    val general: GeneralModifiers = GeneralModifiers(),             // 技能、詠唱、其他通用

    val job: JobBranch,
    val applyJobBonus: Boolean = true
)
