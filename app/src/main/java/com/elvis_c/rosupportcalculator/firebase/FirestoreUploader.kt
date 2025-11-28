package com.elvis_c.rosupportcalculator.firebase

import com.google.firebase.firestore.FirebaseFirestore

object FirestoreUploader {

    private val db = FirebaseFirestore.getInstance()

    fun uploadJupiterLT() {

        val jupiterLT = buildJupiterLT()

        // 寫入 Firestore
        db.collection("equipment_official")
            .document("jupiter_lt")
            .set(jupiterLT)
            .addOnSuccessListener {
                println("【成功】朱比特輕甲-LT 已成功寫入 Firestore！")
            }
            .addOnFailureListener {
                println("【失敗】寫入失敗：${it.message}")
            }
    }

    private fun buildJupiterLT(): Map<String, Any> {

        val effects = listOf(
            // MATK +150
            mapOf(
                "target" to "matk",
                "type" to "Flat",
                "value" to 150.0
            ),

            // 精煉每+2 → MATK+15
            mapOf(
                "target" to "matk",
                "type" to "Flat",
                "value" to 15.0,
                "trigger" to mapOf(
                    "type" to "Refine",
                    "min" to 0,
                    "every" to 2
                )
            ),

            // 精煉每+3 → 特定技能 +20%
            mapOf(
                "target" to "skillDamage",
                "type" to "Percent",
                "value" to 20.0,
                "skills" to listOf(
                    "soul_explosion",
                    "hell_fire",
                    "diamond_dust",
                    "earth_grave"
                ),
                "trigger" to mapOf(
                    "type" to "Refine",
                    "min" to 0,
                    "every" to 3
                )
            ),

            // 精煉+9 → MATK% +15%
            mapOf(
                "target" to "matkPercent",
                "type" to "Percent",
                "value" to 15.0,
                "trigger" to mapOf(
                    "type" to "Refine",
                    "min" to 9
                )
            ),

            // 精煉+11 → 全種族魔法傷害 +15%
            mapOf(
                "target" to "magicRaceDamage",
                "type" to "Percent",
                "value" to 15.0,
                "trigger" to mapOf(
                    "type" to "Refine",
                    "min" to 11
                )
            ),

            // 精煉+13 → 技能後延遲 -15%
            mapOf(
                "target" to "afterSkillDelay",
                "type" to "Percent",
                "value" to -15.0,
                "trigger" to mapOf(
                    "type" to "Refine",
                    "min" to 13
                )
            )
        )


        val gradeEffects = mapOf(
            "D" to listOf(
                mapOf(
                    "target" to "matk",
                    "type" to "Flat",
                    "value" to 8.0,
                    "trigger" to mapOf("type" to "Refine", "every" to 2)
                ),
                mapOf(
                    "target" to "magicSizeDamage",
                    "type" to "Percent",
                    "value" to 4.0,
                    "trigger" to mapOf("type" to "Refine", "every" to 3)
                )
            ),

            "C" to listOf(
                mapOf(
                    "target" to "cooldownReduceSec",
                    "type" to "Flat",
                    "value" to 7.0,
                    "skills" to listOf("ultimate_punch"),
                    "trigger" to mapOf("type" to "Refine", "every" to 3)
                ),
                mapOf(
                    "target" to "cooldownReduceSec",
                    "type" to "Flat",
                    "value" to 0.2,
                    "skills" to listOf("diamond_dust"),
                    "trigger" to mapOf("type" to "Refine", "every" to 3)
                )
            ),

            "B" to listOf(
                mapOf(
                    "target" to "sMatk",
                    "type" to "Flat",
                    "value" to 1.0,
                    "trigger" to mapOf("type" to "Refine", "every" to 2)
                ),
                mapOf(
                    "target" to "magicSizeDamage",
                    "type" to "Percent",
                    "value" to 5.0,
                    "trigger" to mapOf("type" to "Refine", "every" to 3)
                )
            ),

            "A" to listOf(
                mapOf(
                    "target" to "skillDamage",
                    "type" to "Percent",
                    "value" to 7.0,
                    "skills" to listOf("soul_combo", "diamond_storm"),
                    "trigger" to mapOf("type" to "Refine", "every" to 3)
                ),
                mapOf(
                    "target" to "cooldownReduceSec",
                    "type" to "Flat",
                    "value" to 30.0,
                    "skills" to listOf("ultimate_punch"),
                    "trigger" to mapOf("type" to "Refine", "min" to 13)
                ),
                mapOf(
                    "target" to "cooldownReduceSec",
                    "type" to "Flat",
                    "value" to 1.0,
                    "skills" to listOf("diamond_dust"),
                    "trigger" to mapOf("type" to "Refine", "min" to 13)
                )
            )
        )


        val setBonuses = listOf(
            mapOf(
                "cardId" to "mummy_dog",
                "effects" to listOf(
                    mapOf("target" to "matk", "type" to "Flat", "value" to 100.0),
                    mapOf("target" to "mdef", "type" to "Flat", "value" to 10.0),
                    mapOf("target" to "magicRaceDamage", "type" to "Percent", "value" to 15.0)
                )
            ),
            mapOf(
                "cardId" to "fallen_bishop",
                "effects" to listOf(
                    mapOf("target" to "matk", "type" to "Flat", "value" to 100.0),
                    mapOf("target" to "spPercent", "type" to "Percent", "value" to 50.0),
                    mapOf("target" to "mdef", "type" to "Flat", "value" to 20.0),
                    mapOf("target" to "magicRaceDamage", "type" to "Percent", "value" to 30.0)
                )
            )
        )


        val enchantSlots = listOf(
            mapOf("slot" to 2, "allowedGroups" to listOf("SorceryCluster"), "maxLevel" to 3),
            mapOf("slot" to 3, "allowedGroups" to listOf("MagicNebula"), "maxLevel" to 3),
            mapOf("slot" to 4, "allowedGroups" to listOf("MagicNiv"), "maxLevel" to 3)
        )


        return mapOf(
            "id" to "jupiter_lt",
            "name" to "朱比特輕甲-LT",
            "category" to "Armor",
            "armorLevel" to 2,
            "weight" to 30,

            "availableGrades" to listOf("N", "D", "C", "B", "A"),
            "gradeMax" to "A",

            "effects" to effects,
            "gradeEffects" to gradeEffects,
            "setBonuses" to setBonuses,
            "enchantSlots" to enchantSlots,

            "source" to "official",
            "createdBy" to "system",
            "createdAt" to System.currentTimeMillis(),
            "updatedAt" to System.currentTimeMillis()
        )
    }
}