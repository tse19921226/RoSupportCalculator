package com.elvis_c.sharedcalculaotr.equipment.effect

import kotlinx.serialization.Serializable

@Serializable
enum class EffectTarget {

    // ── 攻擊類 ────────────────────────────────
    atk,                   // 物攻（固定值）
    atkPercent,            // 物攻%（後段）
    matk,                  // 魔攻（固定值）
    matkPercent,           // 魔攻%（後段）
    pAtk,                  // P.ATK（物理特攻）
    sMatk,                 // S.MATK（魔法特性攻擊）

    // ── 種族 / 體型 / 屬性 / 特定魔物 ────────────
    raceDamage,            // 對種族增傷
    magicRaceDamage,       // 對種族魔法傷害
    sizeDamage,            // 對體型增傷
    magicSizeDamage,       // 對體型魔法傷害
    classDamage,           // 對階級增傷
    elementMagicDamage,    // 屬性魔法傷害（全屬性）
    specificMonsterDamage, // 特定魔物增傷（例如 MVP/特定 ID）

    // ── 爆擊 ────────────────────────────────
    critDamage,            // 爆傷％
    critRate,              // 爆擊率％

    // ── 無視防禦 ────────────────────────────────
    ignoreDef,             // 無視物防％
    ignoreMDef,            // 無視魔防％
    ignoreTraitPhyDef,     // 無視物理特性防禦（Trait）
    ignoreTraitMagDef,     // 無視魔法特性防禦（Trait）

    // ── 技能強化 / 延遲 ──────────────────────
    skillDamage,           // 技能增傷％
    delayReduce,           // 技能後延遲減少％
    independentDelayReduce,// 技能獨立延遲減少％
    cooldownReduceSec,     // 冷卻時間減少秒數

    // ── 詠唱 ────────────────────────────────
    variableCastPercent,   // 變動詠唱減少%
    variableCastSec,       // 變動詠唱減少秒
    fixedCastPercent,      // 固定詠唱減少%
    fixedCastSec,          // 固定詠唱減少秒

    // ── 攻速 ────────────────────────────────
    aspd,                  // ASPD
    afterSkillDelay,       // 技能後延遲（技能後硬直）

    // ── 六大素質 ────────────────────────────
    str, agi, vit, int, dex, luk,
    allStats,              // 全素質 +1/+2/+3...

    // ── 六大特性（200 等後）────────────────
    pow, sta, wis, spl, con, crt,
    allTraitStats,         // 全特性素質

    // ── 生存 ─────────────────────────────────
    hp, hpPercent,
    sp, spPercent,

    // ── 防禦 ─────────────────────────────────
    mdef
}