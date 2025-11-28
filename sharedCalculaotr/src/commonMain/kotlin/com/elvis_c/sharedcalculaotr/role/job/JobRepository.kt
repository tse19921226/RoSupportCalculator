package com.elvis_c.sharedcalculaotr.role.job

import com.elvis_c.sharedcalculaotr.role.BaseStats
import com.elvis_c.sharedcalculaotr.role.CharacterStatus
import com.elvis_c.sharedcalculaotr.role.TraitStats
import com.elvis_c.sharedcalculaotr.role.plus

object JobRepository {

    val jobs: List<JobInfo> = listOf(

        JobInfo(
            branch = JobBranch.RuneKnight,
            displayNames = "盧恩龍爵/盧恩騎士/騎士",
            baseStatBonus = BaseStats(str = 6, agi = 8, vit = 7, int = 8, dex = 8, luk = 6),
            traitStatBonus = TraitStats(pow = 10, sta = 6, wis = 3, spl = 5, con = 6, crt = 8)
        ),

        JobInfo(
            branch = JobBranch.GuillotineCross,
            displayNames = "十字影武/十字斬首者/刺客",
            baseStatBonus = BaseStats(str = 8, agi = 11, vit = 6, int = 5, dex = 9, luk = 4),
            traitStatBonus = TraitStats(pow = 12, sta = 8, wis = 4, spl = 0, con = 7, crt = 7)
        ),

        JobInfo(
            branch = JobBranch.ArchBishop,
            displayNames = "樞機主教/大主教/祭司",
            baseStatBonus = BaseStats(str = 6, agi = 7, vit = 7, int = 12, dex = 7, luk = 4),
            traitStatBonus = TraitStats(pow = 8, sta = 5, wis = 5, spl = 9, con = 4, crt = 7)
        ),

        JobInfo(
            branch = JobBranch.Ranger,
            displayNames = "風鷹狩獵者/遊俠/獵人",
            baseStatBonus = BaseStats(str = 2, agi = 12, vit = 8, int = 9, dex = 8, luk = 4),
            traitStatBonus = TraitStats(pow = 9, sta = 5, wis = 5, spl = 4, con = 11, crt = 4)
        ),

        JobInfo(
            branch = JobBranch.Warlock,
            displayNames = "禁咒魔導士/咒術士/巫師",
            baseStatBonus = BaseStats(str = 1, agi = 7, vit = 8, int = 15, dex = 8, luk = 4),
            traitStatBonus = TraitStats(pow = 0, sta = 8, wis = 7, spl = 13, con = 9, crt = 1)
        ),

        JobInfo(
            branch = JobBranch.Mechanic,
            displayNames = "機甲神匠/機械工匠/鐵匠",
            baseStatBonus = BaseStats(str = 10, agi = 6, vit = 10, int = 6, dex = 5, luk = 6),
            traitStatBonus = TraitStats(pow = 9, sta = 10, wis = 5, spl = 0, con = 7, crt = 7)
        ),

        JobInfo(
            branch = JobBranch.RoyalGuard,
            displayNames = "帝國禁衛軍/皇家禁衛軍/十字軍",
            baseStatBonus = BaseStats(str = 9, agi = 3, vit = 9, int = 10, dex = 9, luk = 3),
            traitStatBonus = TraitStats(pow = 7, sta = 11, wis = 6, spl = 7, con = 4, crt = 33)
        ),

        JobInfo(
            branch = JobBranch.ShadowChaser,
            displayNames = "深淵追跡者/魅影追蹤者/流氓",
            baseStatBonus = BaseStats(str = 8, agi = 9, vit = 8, int = 6, dex = 6, luk = 6),
            traitStatBonus = TraitStats(pow = 8, sta = 8, wis = 4, spl = 7, con = 5, crt = 6)
        ),

        JobInfo(
            branch = JobBranch.Sura,
            displayNames = "聖裁者/修羅/武僧",
            baseStatBonus = BaseStats(str = 10, agi = 10, vit = 6, int = 5, dex = 8, luk = 1),
            traitStatBonus = TraitStats(pow = 11, sta = 8, wis = 5, spl = 3, con = 5, crt = 6)
        ),

        JobInfo(
            branch = JobBranch.Minstrel,
            displayNames = "天籟頌者/宮廷樂師/詩人",
            baseStatBonus = BaseStats(str = 7, agi = 7, vit = 7, int = 9, dex = 10, luk = 3),
            traitStatBonus = TraitStats(pow = 6, sta = 7, wis = 4, spl = 6, con = 11, crt = 4)
        ),

        JobInfo(
            branch = JobBranch.Wanderer,
            displayNames = "樂之舞靈/浪跡舞者/舞孃",
            baseStatBonus = BaseStats(str = 7, agi = 9, vit = 6, int = 10, dex = 8, luk = 3),
            traitStatBonus = TraitStats(pow = 6, sta = 7, wis = 4, spl = 6, con = 11, crt = 4)
        ),

        JobInfo(
            branch = JobBranch.Sorcerer,
            displayNames = "元素支配者/妖術師/賢者",
            baseStatBonus = BaseStats(str = 4, agi = 4, vit = 8, int = 13, dex = 9, luk = 5),
            traitStatBonus = TraitStats(pow = 3, sta = 8, wis = 7, spl = 12, con = 5, crt = 3)
        ),

        JobInfo(
            branch = JobBranch.Genetic,
            displayNames = "生命締造者/基因學者/煉金術師",
            baseStatBonus = BaseStats(str = 5, agi = 6, vit = 8, int = 12, dex = 8, luk = 4),
            traitStatBonus = TraitStats(pow = 7, sta = 4, wis = 4, spl = 4, con = 7, crt = 12)
        ),

        JobInfo(
            branch = JobBranch.Kagerou,
            displayNames = "流浪忍者/影狼",
            baseStatBonus = BaseStats(str = 10, agi = 12, vit = 6, int = 4, dex = 9, luk = 3),
            traitStatBonus = TraitStats(pow = 10, sta = 10, wis = 4, spl = 0, con = 6, crt = 8)
        ),

        JobInfo(
            branch = JobBranch.Oboro,
            displayNames = "疾風忍者/朧",
            baseStatBonus = BaseStats(str = 4, agi = 8, vit = 5, int = 10, dex = 10, luk = 3),
            traitStatBonus = TraitStats(pow = 4, sta = 8, wis = 10, spl = 3, con = 6, crt = 7)
        ),

        JobInfo(
            branch = JobBranch.Rebellion,
            displayNames = "夜行使/反叛者",
            baseStatBonus = BaseStats(str = 3, agi = 8, vit = 6, int = 8, dex = 11, luk = 7),
            traitStatBonus = TraitStats(pow = 11, sta = 6, wis = 5, spl = 0, con = 10, crt = 5)
        ),

        JobInfo(
            branch = JobBranch.StarEmperor,
            displayNames = "天帝/拳皇",
            baseStatBonus = BaseStats(str = 12, agi = 10, vit = 6, int = 3, dex = 9, luk = 3),
            traitStatBonus = TraitStats(pow = 12, sta = 10, wis = 2, spl = 0, con = 6, crt = 7)
        ),

        JobInfo(
            branch = JobBranch.SoulAscetic,
            displayNames = "契靈士/獵靈士",
            baseStatBonus = BaseStats(str = 3, agi = 7, vit = 7, int = 11, dex = 13, luk = 2),
            traitStatBonus = TraitStats(pow = 0, sta = 8, wis = 7, spl = 16, con = 7, crt = 3)
        ),

        JobInfo(
            branch = JobBranch.Summoner,
            displayNames = "魂靈師/喵族召喚師",
            baseStatBonus = BaseStats(str = 2, agi = 12, vit = 8, int = 9, dex = 8, luk = 4),
            traitStatBonus = TraitStats(pow = 0, sta = 1, wis = 2, spl = 2, con = 0, crt = 0)
        ),

        JobInfo(
            branch = JobBranch.SuperNovice,
            displayNames = "終極初學者/超級初心者",
            baseStatBonus = BaseStats(str = 10, agi = 5, vit = 6, int = 10, dex = 5, luk = 6),
            traitStatBonus = TraitStats(pow = 9, sta = 5, wis = 4, spl = 9, con = 8, crt = 3)
        )
    )

    fun get(branch: JobBranch): JobInfo =
        jobs.first { it.branch == branch }

}

