package com.elvis_c.sharedcalculaotr.engine.skill.warlock


import com.elvis_c.sharedcalculaotr.engine.Element
import com.elvis_c.sharedcalculaotr.engine.skill.SkillComputeDefinition
import com.elvis_c.sharedcalculaotr.engine.skill.SkillComputeResult
import com.elvis_c.sharedcalculaotr.engine.skill.SkillContext
import com.elvis_c.sharedcalculaotr.engine.skill.SkillEngine
import com.elvis_c.sharedcalculaotr.engine.skill.SkillVariantResult

object WarlockSkillCompute {

    // --- 靈魂連擊 ---
    val SoulComboCompute = SkillComputeDefinition(
        skillId = "soul_combo",
        compute = { ctx: SkillContext ->

            // 原始公式：(SLV*300 + SPL*3) * BLV / 100
            val rawPercent = (ctx.slv * 300 + ctx.spl * 3) * ctx.blv / 100.0
            val ratePerHit = rawPercent / 100.0   // 4680% → 46.8

            SkillComputeResult(
                variants = listOf(
                    SkillVariantResult(
                        key = "default",
                        label = "一般傷害",
                        element = Element.Ghost,   // 念屬
                        ratePerHit = ratePerHit,
                        hits = ctx.slv + 2         // SLV + 2 HIT
                    )
                )
            )
        }
    )

    // --- 靈魂爆炸 ---
    val SoulExplosionCompute = SkillComputeDefinition(
        skillId = "soul_explosion",
        compute = { ctx: SkillContext ->

            // 原始公式：(1000 + SLV*200 + INT) * BLV / 100
            val rawPercent = (1000 + ctx.slv * 200 + ctx.int) * ctx.blv / 100.0
            val ratePerHit = rawPercent / 100.0

            SkillComputeResult(
                variants = listOf(
                    SkillVariantResult(
                        key = "default",
                        label = "一般傷害",
                        element = Element.Ghost,   // 念屬
                        ratePerHit = ratePerHit,
                        hits = 2                   // 固定 2 HIT
                    )
                )
            )
        }
    )

    // --- 碧血隕石 ---
    val BloodMeteorCompute = SkillComputeDefinition(
        skillId = "blood_meteor",
        compute = { ctx ->

            val raw = (700 + ctx.slv * 600) * ctx.blv / 100.0
            val rate = raw / 100.0       // 換成倍率：例如 4200% → 42.0

            SkillComputeResult(
                variants = listOf(
                    SkillVariantResult(
                        key = "default",
                        label = "一般傷害",
                        element = Element.Fire,
                        ratePerHit = rate,
                        hits = 7
                    )
                )
            )
        }
    )

    // --- 地牛翻身 ---
    val EarthShockCompute = SkillComputeDefinition(
        skillId = "earth_shock",
        compute = { ctx ->

            val raw = (1000 + ctx.slv * 600) * ctx.blv / 100.0
            val rate = raw / 100.0

            SkillComputeResult(
                variants = listOf(
                    SkillVariantResult(
                        key = "default",
                        label = "一般傷害",
                        element = Element.Earth,
                        ratePerHit = rate,
                        hits = 10
                    )
                )
            )
        }
    )

    // --- 凍僵術 ---
    val FrostBiteCompute = SkillComputeDefinition(
        skillId = "frost_bite",
        compute = { ctx ->

            val raw = (1000 + ctx.slv * 300) * ctx.blv / 100.0
            val rate = raw / 100.0

            SkillComputeResult(
                variants = listOf(
                    SkillVariantResult(
                        key = "default",
                        label = "一般傷害",
                        element = Element.Water,
                        ratePerHit = rate,
                        hits = 5
                    )
                )
            )
        }
    )

    // --- 凍僵術 ---
    val FrostBiteFrozenCompute = SkillComputeDefinition(
        skillId = "frost_bite_frozen",
        compute = { ctx ->

            val raw = (1200 + ctx.slv * 600) * ctx.blv / 100.0
            val rate = raw / 100.0

            SkillComputeResult(
                variants = listOf(
                    SkillVariantResult(
                        key = "default",
                        label = "結霜傷害",
                        element = Element.Water,
                        ratePerHit = rate,
                        hits = 5
                    )
                )
            )
        }
    )

    // --- 連鎖電擊 ---
    val ChainLightningCompute = SkillComputeDefinition(
        skillId = "chain_lightning",
        compute = { ctx ->

            val raw = (500 + ctx.slv * 100) * ctx.blv / 100.0 + 800
            val rate = raw / 100.0

            SkillComputeResult(
                variants = listOf(
                    SkillVariantResult(
                        key = "default",
                        label = "一般傷害",
                        element = Element.Wind,
                        ratePerHit = rate,
                        hits = 1
                    )
                )
            )
        }
    )

    // --- 地獄火焰 ---
    val HellFireCompute = SkillComputeDefinition(
        skillId = "hell_fire",
        compute = { ctx ->

            // ---- 暗屬公式 ----
            val rawDark = (ctx.slv * 600) * ctx.blv / 100.0
            val rateDark = rawDark / 100.0

            // ---- 火屬公式 ----
            val rawFire = (ctx.slv * 400) * ctx.blv / 100.0
            val rateFire = rawFire / 100.0

            SkillComputeResult(
                variants = listOf(
                    SkillVariantResult(
                        key = "dark",
                        label = "暗屬性傷害",
                        element = Element.Dark,
                        ratePerHit = rateDark,
                        hits = 3         // 暗屬是 -3 HIT → 3 hits
                    ),
                    SkillVariantResult(
                        key = "fire",
                        label = "火屬性傷害",
                        element = Element.Fire,
                        ratePerHit = rateFire,
                        hits = 1
                    )
                )
            )
        }
    )

    // --- 毀滅慧星 ---
    val DoomsdayMeteorCompute = SkillComputeDefinition(
        skillId = "doomsday_meteor",
        compute = { ctx ->

            val raw = (3000 + ctx.slv * 600) * ctx.blv / 100.0
            val rate = raw / 100.0

            SkillComputeResult(
                variants = listOf(
                    SkillVariantResult(
                        key = "default",
                        label = "一般傷害",
                        element = Element.Neutral,
                        ratePerHit = rate,
                        hits = 10
                    )
                )
            )
        }
    )

    // --- 焰火之徑 ---
    val FlameRoadCompute = SkillComputeDefinition(
        skillId = "flame_road",
        compute = { ctx ->

            val raw = (50 + ctx.slv * 740 + ctx.spl * 5) * ctx.blv / 100.0
            val rate = raw / 100.0

            SkillComputeResult(
                variants = listOf(
                    SkillVariantResult(
                        key = "default",
                        label = "一般傷害",
                        element = Element.Fire,
                        ratePerHit = rate,
                        hits = 5
                    )
                )
            )
        }
    )

    // --- 豔紅魔矢（主技能） ---
    val CrimsonArrowCompute = SkillComputeDefinition(
        skillId = "crimson_arrow",
        compute = { ctx ->

            // 主段：(SLV*400 + SPL*3) * BLV / 100
            val rawMain = (ctx.slv * 400 + ctx.spl * 3) * ctx.blv / 100.0
            val rateMain = rawMain / 100.0

            // 爆炸：(SLV*750 + SPL*5) * BLV / 100
            val rawExplosion = (ctx.slv * 750 + ctx.spl * 5) * ctx.blv / 100.0
            val rateExplosion = rawExplosion / 100.0

            // 總和(一般)：1 主段 + 1 爆炸
            val rateTotalNormal = rateMain + rateExplosion

            // 總和(巔峰)：1 主段 + 2 爆炸
            val rateTotalPeak = rateMain + rateExplosion * 2

            SkillComputeResult(
                variants = listOf(
                    // 沒有巔峰狀態：1主段 + 1爆炸
                    SkillVariantResult(
                        key = "total",
                        label = "總和(一般)",
                        element = Element.Fire,
                        ratePerHit = rateTotalNormal,
                        hits = 1
                    ),
                    // 魔力巔峰狀態：1主段 + 2爆炸
                    SkillVariantResult(
                        key = "total_peak",
                        label = "總和(巔峰)",
                        element = Element.Fire,
                        ratePerHit = rateTotalPeak,
                        hits = 1
                    ),
                    // 純爆炸倍率（給裝備比較用）
                    SkillVariantResult(
                        key = "explosion",
                        label = "爆炸傷害",
                        element = Element.Fire,
                        ratePerHit = rateExplosion,
                        hits = 2   // 技能本身爆炸 2 HIT
                    )
                )
            )
        }
    )

    // --- 萬紫千紅 ---
    val ThousandThornsCompute = SkillComputeDefinition(
        skillId = "thousand_thorns",
        compute = { ctx ->

            // --- 一般版 raw ---
            val rawNormal = (200 + ctx.slv * 1200 + ctx.spl * 5) * ctx.blv / 100.0
            val rateNormal = rawNormal / 100.0

            // --- 巔峰5 raw ---
            val rawPeak5 = (200 + ctx.slv * 1200 + ctx.spl * 5 + 85000) * ctx.blv / 100.0
            val ratePeak5 = rawPeak5 / 100.0

            val hits = ctx.slv * 2

            SkillComputeResult(
                variants = listOf(
                    SkillVariantResult(
                        key = "normal",
                        label = "一般傷害",
                        element = Element.Neutral, // 若你確定屬性是別的我可更新
                        ratePerHit = rateNormal,
                        hits = hits
                    ),
                    SkillVariantResult(
                        key = "peak5",
                        label = "巔峰5傷害",
                        element = Element.Neutral,
                        ratePerHit = ratePeak5,
                        hits = hits
                    )
                )
            )
        }
    )

    // --- 冰晶飛瀑 ---
    val IceCascadeCompute = SkillComputeDefinition(
        skillId = "ice_cascade",
        compute = { ctx ->

            val raw = (180 + ctx.slv * 760 + ctx.spl * 5) * ctx.blv / 100.0
            val rate = raw / 100.0

            SkillComputeResult(
                variants = listOf(
                    SkillVariantResult(
                        key = "default",
                        label = "一般傷害",
                        element = Element.Water,
                        ratePerHit = rate,
                        hits = 8
                    )
                )
            )
        }
    )

    // --- 冰刃斬 ---
    val IceSlashCompute = SkillComputeDefinition(
        skillId = "ice_slash",
        compute = { ctx ->

            val rawNormal = (400 + ctx.slv * 950 + ctx.spl * 5) * ctx.blv / 100.0
            val rateNormal = rawNormal / 100.0

            val rawPeak = (550 + ctx.slv * 1300 + ctx.spl * 5) * ctx.blv / 100.0
            val ratePeak = rawPeak / 100.0

            SkillComputeResult(
                variants = listOf(
                    SkillVariantResult(
                        key = "normal",
                        label = "一般傷害",
                        element = Element.Water,
                        ratePerHit = rateNormal,
                        hits = 3
                    ),
                    SkillVariantResult(
                        key = "peak",
                        label = "巔峰傷害",
                        element = Element.Water,
                        ratePerHit = ratePeak,
                        hits = 3
                    )
                )
            )
        }
    )

    // --- 水晶波爆 ---
    val CrystalBurstCompute = SkillComputeDefinition(
        skillId = "crystal_burst",
        compute = { ctx ->

            val rawNormal = (250 + ctx.slv * 1300 + ctx.spl * 5) * ctx.blv / 100.0
            val rateNormal = rawNormal / 100.0

            val hitsNormal = 2
            val hitsPeak2 = 3   // 巔峰 2

            SkillComputeResult(
                variants = listOf(
                    SkillVariantResult(
                        key = "normal",
                        label = "一般傷害",
                        element = Element.Water,
                        ratePerHit = rateNormal,
                        hits = hitsNormal
                    ),
                    SkillVariantResult(
                        key = "peak2",
                        label = "巔峰2傷害",
                        element = Element.Water,
                        ratePerHit = rateNormal,
                        hits = hitsPeak2
                    )
                )
            )
        }
    )

    // --- 龍捲風暴 ---
    val TornadoStormCompute = SkillComputeDefinition(
        skillId = "tornado_storm",
        compute = { ctx ->

            val raw = (100 + ctx.slv * 760 + ctx.spl * 5) * ctx.blv / 100.0
            val rate = raw / 100.0

            SkillComputeResult(
                variants = listOf(
                    SkillVariantResult(
                        key = "default",
                        label = "一般傷害",
                        element = Element.Wind,
                        ratePerHit = rate,
                        hits = 10
                    )
                )
            )
        }
    )

    // --- 暴風加農砲 ---
    val StormCannonCompute = SkillComputeDefinition(
        skillId = "storm_cannon",
        compute = { ctx ->

            // --- 一般倍率 ---
            val rawNormal = (ctx.slv * 1550 + ctx.spl * 5) * ctx.blv / 100.0
            val rateNormal = rawNormal / 100.0

            // --- 巔峰倍率 ---
            val rawPeak = (ctx.slv * 1850 + ctx.spl * 5) * ctx.blv / 100.0
            val ratePeak = rawPeak / 100.0

            val hits = 2 // -2 HIT → 2 hits

            SkillComputeResult(
                variants = listOf(
                    SkillVariantResult(
                        key = "normal",
                        label = "一般傷害",
                        element = Element.Wind,
                        ratePerHit = rateNormal,
                        hits = hits
                    ),
                    SkillVariantResult(
                        key = "peak",
                        label = "巔峰傷害",
                        element = Element.Wind,
                        ratePerHit = ratePeak,
                        hits = hits
                    )
                )
            )
        }
    )

    // --- 毀滅颶風 ---
    val DestructionGaleCompute = SkillComputeDefinition(
        skillId = "destruction_gale",
        compute = { ctx ->

            // 一般
            val rawNormal = (600 + ctx.slv * 2850 + ctx.spl * 5) * ctx.blv / 100.0
            val rateNormal = rawNormal / 100.0

            // 巔峰1
            val rawPeak = (600 + ctx.slv * 2850 + ctx.spl * 5 + 12500) * ctx.blv / 100.0
            val ratePeak = rawPeak / 100.0

            val hits = 10

            SkillComputeResult(
                variants = listOf(
                    SkillVariantResult(
                        key = "normal",
                        label = "一般傷害",
                        element = Element.Wind,
                        ratePerHit = rateNormal,
                        hits = hits
                    ),
                    SkillVariantResult(
                        key = "peak1",
                        label = "巔峰1傷害",
                        element = Element.Wind,
                        ratePerHit = ratePeak,
                        hits = hits
                    )
                )
            )
        }
    )

    // --- 地層震動 ---
    val SeismicWaveCompute = SkillComputeDefinition(
        skillId = "seismic_wave",
        compute = { ctx ->

            val raw = (100 + ctx.slv * 730 + ctx.spl * 5) * ctx.blv / 100.0
            val rate = raw / 100.0

            SkillComputeResult(
                variants = listOf(
                    SkillVariantResult(
                        key = "default",
                        label = "一般傷害",
                        element = Element.Earth,
                        ratePerHit = rate,
                        hits = 5
                    )
                )
            )
        }
    )

    // --- 巨石降臨 ---
    val MeteoriteFallCompute = SkillComputeDefinition(
        skillId = "meteorite_fall",
        compute = { ctx ->

            // 一般倍率
            val rawNormal = (ctx.slv * 1550 + ctx.spl * 5) * ctx.blv / 100.0
            val rateNormal = rawNormal / 100.0

            // 巔峰倍率
            val rawPeak = (ctx.slv * 1850 + ctx.spl * 5) * ctx.blv / 100.0
            val ratePeak = rawPeak / 100.0

            val hits = 5   // -5 HIT → 5 hits

            SkillComputeResult(
                variants = listOf(
                    SkillVariantResult(
                        key = "normal",
                        label = "一般傷害",
                        element = Element.Earth,
                        ratePerHit = rateNormal,
                        hits = hits
                    ),
                    SkillVariantResult(
                        key = "peak",
                        label = "巔峰傷害",
                        element = Element.Earth,
                        ratePerHit = ratePeak,
                        hits = hits
                    )
                )
            )
        }
    )

    // --- 震裂術 ---
    val RuptureCompute = SkillComputeDefinition(
        skillId = "rupture",
        compute = { ctx ->

            // 基礎倍率
            val raw = (ctx.slv * 950 + ctx.spl * 5) * ctx.blv / 100.0
            val rate = raw / 100.0

            val hitsNormal = ctx.slv * 4
            val hitsPeak = ctx.slv * 8

            SkillComputeResult(
                variants = listOf(
                    SkillVariantResult(
                        key = "normal",
                        label = "一般傷害",
                        element = Element.Earth,
                        ratePerHit = rate,
                        hits = hitsNormal
                    ),
                    SkillVariantResult(
                        key = "peak1",
                        label = "巔峰1傷害",
                        element = Element.Earth,
                        ratePerHit = rate,
                        hits = hitsPeak
                    )
                )
            )
        }
    )

    // --- 致命放射 ---
    val FatalRayCompute = SkillComputeDefinition(
        skillId = "fatal_ray",
        compute = { ctx ->

            val raw = (ctx.slv * 2800 + ctx.spl * 5) * ctx.blv / 100.0
            val rate = raw / 100.0

            SkillComputeResult(
                variants = listOf(
                    SkillVariantResult(
                        key = "default",
                        label = "一般傷害",
                        element = Element.Undead,
                        ratePerHit = rate,
                        hits = 1
                    )
                )
            )
        }
    )

    // --- 神秘幻滅 ---
    val MysticRuinCompute = SkillComputeDefinition(
        skillId = "mystic_ruin",
        compute = { ctx ->

            val raw = (ctx.slv * 950 + ctx.spl * 5) * ctx.blv / 100.0
            val rate = raw / 100.0

            val hits = 14  // 預設 14，其他段數給 UI 顯示用

            SkillComputeResult(
                variants = listOf(
                    SkillVariantResult(
                        key = "default",
                        label = "一般傷害",
                        element = Element.Dark,
                        ratePerHit = rate,
                        hits = hits
                    )
                )
            )
        }
    )

    // --- 星際突襲 ---
    val StarAssaultCompute = SkillComputeDefinition(
        skillId = "star_assault",
        compute = { ctx ->

            // ========== 一般版 主段 ==========
            val rawNormal = (300 + ctx.slv * 1800 + ctx.spl * 3) * ctx.blv / 100.0
            val rateNormal = rawNormal / 100.0

            // ========== 一般版 追加段 ==========
            // (650 + SLV*10 + SPL*3) * BLV / 100
            val rawBonus = (650 + ctx.slv * 10 + ctx.spl * 3) * ctx.blv / 100.0
            val rateBonus = rawBonus / 100.0

            // 總和 = 主段 + 追加傷害*12
            val rateNormalTotal = rateNormal + (rateBonus * 12)

            // ========== 不死/龍 主段 ==========
            val rawUD = (400 + ctx.slv * 2100 + ctx.spl * 3) * ctx.blv / 100.0
            val rateUD = rawUD / 100.0

            // 不死/龍 總和
            val rateUDTotal = rateUD + (rateBonus * 12)

            SkillComputeResult(
                variants = listOf(

                    // ① 一般主段
                    SkillVariantResult(
                        key = "normal",
                        label = "一般主段",
                        element = Element.Neutral,
                        ratePerHit = rateNormal,
                        hits = 1
                    ),

                    // ② 一般總和（主段 + 12 hit）
                    SkillVariantResult(
                        key = "normal_total",
                        label = "一般總和",
                        element = Element.Neutral,
                        ratePerHit = rateNormalTotal,
                        hits = 1
                    ),

                    // ③ 不死/龍 主段
                    SkillVariantResult(
                        key = "undead_dragon",
                        label = "不死型/龍 主段",
                        element = Element.Neutral,
                        ratePerHit = rateUD,
                        hits = 1
                    ),

                    // ④ 不死/龍 總和（主段 + 12 hit）
                    SkillVariantResult(
                        key = "undead_dragon_total",
                        label = "不死型/龍 總和",
                        element = Element.Neutral,
                        ratePerHit = rateUDTotal,
                        hits = 1
                    )
                )
            )
        }
    )

    fun registerAll() {
        SkillEngine.register(SoulComboCompute)
        SkillEngine.register(SoulExplosionCompute)
        SkillEngine.register(BloodMeteorCompute)
        SkillEngine.register(EarthShockCompute)
        SkillEngine.register(FrostBiteCompute)
        SkillEngine.register(FrostBiteFrozenCompute)
        SkillEngine.register(ChainLightningCompute)
        SkillEngine.register(HellFireCompute)
        SkillEngine.register(DoomsdayMeteorCompute)
        SkillEngine.register(FlameRoadCompute)
        SkillEngine.register(CrimsonArrowCompute)
        SkillEngine.register(ThousandThornsCompute)
        SkillEngine.register(IceCascadeCompute)
        SkillEngine.register(IceSlashCompute)
        SkillEngine.register(CrystalBurstCompute)
        SkillEngine.register(TornadoStormCompute)
        SkillEngine.register(StormCannonCompute)
        SkillEngine.register(DestructionGaleCompute)
        SkillEngine.register(SeismicWaveCompute)
        SkillEngine.register(MeteoriteFallCompute)
        SkillEngine.register(RuptureCompute)
        SkillEngine.register(FatalRayCompute)
        SkillEngine.register(MysticRuinCompute)
        SkillEngine.register(StarAssaultCompute)
    }
}