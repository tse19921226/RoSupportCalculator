package com.elvis_c.sharedcalculaotr.role.job

import kotlinx.serialization.Serializable

@Serializable
enum class JobBranch {
    RuneKnight,         // 盧恩龍爵（騎士線）
    GuillotineCross,    // 十字影武（刺客線）
    ArchBishop,         // 樞機主教（祭司線）
    Ranger,             // 風鷹狩獵者（獵人線）
    Warlock,            // 禁咒魔導士（巫師線）
    Mechanic,           // 機甲神匠（鐵匠線）
    RoyalGuard,         // 帝國禁衛軍（十字軍線）
    ShadowChaser,       // 深淵追跡者（流氓線）
    Sura,               // 聖裁者（修羅線）
    Minstrel,           // 天籟頌者（詩人線）
    Wanderer,           // 樂之舞靈（舞孃線）
    Sorcerer,           // 元素支配者（賢者線）
    Genetic,            // 生命締造者（煉金術師線）
    Kagerou,            // 流浪忍者（影狼）
    Oboro,              // 疾風忍者（朧）
    Rebellion,          // 夜行使（反叛者）
    StarEmperor,        // 天帝（拳皇）
    SoulAscetic,        // 契靈士（獵靈士）
    Summoner,           // 魂靈師（喵族召喚師）
    SuperNovice         // 終極初學者（超級初心者）
}