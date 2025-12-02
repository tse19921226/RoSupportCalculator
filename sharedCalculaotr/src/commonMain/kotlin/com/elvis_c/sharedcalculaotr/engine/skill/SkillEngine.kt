package com.elvis_c.sharedcalculaotr.engine.skill



object SkillEngine {

    private val computeMap = mutableMapOf<String, SkillComputeDefinition>()

    fun register(def: SkillComputeDefinition) {
        computeMap[def.skillId] = def
    }

    fun compute(skillId: String, ctx: SkillContext): SkillComputeResult {
        val def = computeMap[skillId]
            ?: error("Skill compute not found for skillId=$skillId")

        return def.compute(ctx)
    }
}