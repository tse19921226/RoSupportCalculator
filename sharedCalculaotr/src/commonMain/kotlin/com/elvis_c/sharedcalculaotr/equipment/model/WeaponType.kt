package com.elvis_c.sharedcalculaotr.equipment.model

import kotlinx.serialization.Serializable

@Serializable
enum class WeaponType {
    Dagger,             // 短劍
    OneHandSword,       // 單手劍
    TwoHandSword,       // 雙手劍
    OneHandAxe,         // 單手斧
    TwoHandAxe,         // 雙手斧
    Mace,               // 鈍器
    OneHandSpear,       // 單手槍
    TwoHandSpear,       // 雙手槍
    OneHandStaff,       // 單手杖
    TwoHandStaff,       // 雙手杖
    Bow,                // 弓
    Knuckle,            // 拳刃
    Katar,              // 拳套
    Book,               // 書
    Whip,               // 鞭子
    Musical,            // 樂器
    Revolver,           // 手槍
    Rifle,              // 步槍
    Gatling,            // 格林
    Shotgun,            // 霰彈槍
    GrenadeLauncher,    // 手榴彈發射器
    Huuma,              // 飛鏢扇刃
    None                // 無武器（空手 or 不分類）
}