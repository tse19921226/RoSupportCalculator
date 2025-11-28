package com.elvis_c.sharedcalculaotr.equipment.model

import kotlinx.serialization.Serializable

@Serializable
enum class EquipmentCategory {
    Weapon,         // 武器（短劍、單手劍、弓、法杖...）
    Armor,          // 鎧甲
    Shield,         // 盾牌
    Garment,        // 披肩
    Shoes,          // 鞋子
    Accessory,      // 飾品
    HeadUpper,      // 頭上
    HeadMid,        // 頭中
    HeadLower,      // 頭下
    CostumeUpper,   // 時裝頭上
    CostumeMid,     // 時裝頭中
    CostumeLower,   // 時裝頭下
    ShadowWeapon,   // 影武器
    ShadowArmor,    // 影鎧
    ShadowShield,   // 影盾
    ShadowShoes,    // 影鞋
    ShadowAccessory // 影飾品
}