package com.elvis_c.sharedcalculaotr.equipment.effect


import kotlinx.serialization.Serializable

/**
 * 各種會被用於計算、觸發條件、Scaling 的角色屬性種類。
 *
 * 分成三大類：
 * 1. 六大素質（STR / AGI / VIT / INT / DEX / LUK）
 * 2. 六大特性素質（POW / STA / WIS / SPL / CON / CRT）
 * 3. 合計類型（AllStats / AllTraitStats）
 */
@Serializable
enum class StatType {

    // 六大素質
    STR,   // 力量
    AGI,   // 敏捷
    VIT,   // 體質
    INT,   // 智力
    DEX,   // 靈巧
    LUK,   // 幸運

    // 六大特性素質（200 等後）
    POW,   // 物攻屬性
    STA,   // 防禦屬性
    WIS,   // 支援屬性
    SPL,   // 魔攻屬性
    CON,   // 控制屬性
    CRT,   // 爆擊屬性

    // 合計類型
    AllStats,        // 全素質（STR+AGI+VIT+INT+DEX+LUK）
    AllTraitStats    // 全特性素質（POW+STA+WIS+SPL+CON+CRT）
}