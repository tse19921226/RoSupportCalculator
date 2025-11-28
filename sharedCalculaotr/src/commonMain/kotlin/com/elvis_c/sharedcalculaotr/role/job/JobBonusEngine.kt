package com.elvis_c.sharedcalculaotr.role.job

import com.elvis_c.sharedcalculaotr.role.CharacterStatus
import com.elvis_c.sharedcalculaotr.role.plus

/**
 * 專門負責「職業加成（滿 Job）」的邏輯引擎。
 *
 * 使用者可透過 CharacterStatus.applyJobBonus 來開關。
 * 若開啟 → 套用該職業的 BaseStats & TraitStats 的滿 Job 加成。
 * 若關閉 → 原封不動，直接讓使用者自行輸入素質。
 *
 * 好處：
 * - 避免 CharacterStatus/JobRepository 過度膨脹
 * - 集中維護職業加成邏輯
 * - 後續如要增加 LV200 加成也能加在這裡
 */
object JobBonusEngine {

    /**
     * 套用職業加成（只有滿 Job 版本）
     */
    fun applyBonus(
        char: CharacterStatus
    ): CharacterStatus {
        if (!char.applyJobBonus) return char

        val jobInfo = JobRepository.get(char.job)

        return char.copy(
            baseStats = char.baseStats.plus(jobInfo.baseStatBonus),
            traitStats = char.traitStats.plus(jobInfo.traitStatBonus)
//            baseStats = char.baseStats + jobInfo.baseStatBonus,
//            traitStats = char.traitStats + jobInfo.traitStatBonus
        )
    }
}


/**
 *sample
 *
 * var char = CharacterStatus(
 *     baseStats = BaseStats(...),
 *     traitStats = TraitStats(...),
 *     job = JobBranch.GuillotineCross,
 *     applyJobBonus = true     // 套用滿 Job 加成
 * )
 *
 * 若使用者選擇「沒滿 Job」
 * char = char.copy(applyJobBonus = false)
 * char = JobRepository.applyBonus(char)
 * **/