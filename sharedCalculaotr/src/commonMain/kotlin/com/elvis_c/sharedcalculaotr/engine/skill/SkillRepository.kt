package com.elvis_c.sharedcalculaotr.engine.skill

import com.elvis_c.sharedcalculaotr.role.job.JobBranch

object SkillRepository {

    private val skills = mutableMapOf<String, Skill>()

    fun register(skill: Skill) {
        skills[skill.id] = skill
    }

    fun get(id: String): Skill? = skills[id]

    fun getByJob(job: JobBranch): List<Skill> =
        skills.values.filter { it.job == job }
}