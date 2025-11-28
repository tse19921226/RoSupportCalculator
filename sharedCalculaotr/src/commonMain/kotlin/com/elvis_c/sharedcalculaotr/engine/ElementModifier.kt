package com.elvis_c.sharedcalculaotr.engine

/**
 * key = Pair(攻擊屬性, 防禦屬性階級 Element.X to ElementLevel.LvY)
 * value = 倍率 (100 = 1.0)
 */
object ElementModifier {

    /**
     * 將 0~200 百分比換算成倍率：100 → 1.0
     */
    private fun pct(v: Int) = v / 100.0

    /**
     * 主資料表（依照你的相剋圖製作）
     */
    private val table: Map<Element, Map<Element, List<Int>>> = mapOf(

        Element.Neutral to mapOf(
            Element.Neutral to listOf(100, 100, 100, 100),
            Element.Fire    to listOf(100, 100, 100, 100),
            Element.Water   to listOf(100, 100, 100, 100),
            Element.Wind    to listOf(100, 100, 100, 100),
            Element.Earth   to listOf(100, 100, 100, 100),
            Element.Poison  to listOf(100, 100, 100, 100),
            Element.Holy    to listOf(100, 100, 100, 100),
            Element.Dark    to listOf(100, 100, 100, 100),
            Element.Ghost   to listOf(90, 70, 50, 25),
            Element.Undead  to listOf(100, 100, 100, 100)
        ),

        Element.Fire to mapOf(
            Element.Neutral to listOf(100, 100, 100, 100),
            Element.Fire    to listOf(100, 100, 100, 100),
            Element.Water   to listOf(25, 50, 75, 100),
            Element.Wind    to listOf(150, 175, 200, 200),
            Element.Earth   to listOf(150, 150, 150, 150),
            Element.Poison  to listOf(100, 100, 100, 100),
            Element.Holy    to listOf(100, 100, 100, 100),
            Element.Dark    to listOf(100, 100, 100, 100),
            Element.Ghost   to listOf(90, 70, 50, 25),
            Element.Undead  to listOf(100, 100, 100, 100)
        ),

        Element.Water to mapOf(
            Element.Neutral to listOf(100, 100, 100, 100),
            Element.Fire    to listOf(150, 150, 150, 150),
            Element.Water   to listOf(100, 100, 100, 100),
            Element.Wind    to listOf(25, 50, 75, 100),
            Element.Earth   to listOf(200, 200, 200, 200),
            Element.Poison  to listOf(100, 100, 100, 100),
            Element.Holy    to listOf(100, 100, 100, 100),
            Element.Dark    to listOf(100, 100, 100, 100),
            Element.Ghost   to listOf(90, 70, 50, 25),
            Element.Undead  to listOf(100, 100, 100, 100)
        ),

        Element.Wind to mapOf(
            Element.Neutral to listOf(100, 100, 100, 100),
            Element.Fire    to listOf(25, 25, 25, 25),
            Element.Water   to listOf(200, 200, 200, 200),
            Element.Wind    to listOf(100, 100, 100, 100),
            Element.Earth   to listOf(25, 25, 25, 25),
            Element.Poison  to listOf(100, 100, 100, 100),
            Element.Holy    to listOf(100, 100, 100, 100),
            Element.Dark    to listOf(100, 100, 100, 100),
            Element.Ghost   to listOf(90, 70, 50, 25),
            Element.Undead  to listOf(100, 100, 100, 100)
        ),

        Element.Earth to mapOf(
            Element.Neutral to listOf(100, 100, 100, 100),
            Element.Fire    to listOf(100, 100, 100, 100),
            Element.Water   to listOf(100, 100, 100, 100),
            Element.Wind    to listOf(150, 150, 150, 150),
            Element.Earth   to listOf(100, 100, 100, 100),
            Element.Poison  to listOf(100, 100, 100, 100),
            Element.Holy    to listOf(100, 100, 100, 100),
            Element.Dark    to listOf(100, 100, 100, 100),
            Element.Ghost   to listOf(90, 70, 50, 25),
            Element.Undead  to listOf(100, 100, 100, 100)
        ),

        Element.Poison to mapOf(
            Element.Neutral to listOf(100, 100, 100, 100),
            Element.Fire    to listOf(75, 75, 75, 75),
            Element.Water   to listOf(150, 150, 150, 150),
            Element.Wind    to listOf(150, 150, 150, 150),
            Element.Earth   to listOf(100, 100, 100, 100),
            Element.Poison  to listOf(100, 100, 100, 100),
            Element.Holy    to listOf(75, 75, 75, 75),
            Element.Dark    to listOf(75, 75, 75, 75),
            Element.Ghost   to listOf(75, 50, 25, 0),
            Element.Undead  to listOf(75, 50, 25, 0)
        ),

        Element.Holy to mapOf(
            Element.Neutral to listOf(100, 100, 100, 100),
            Element.Fire    to listOf(100, 100, 100, 100),
            Element.Water   to listOf(100, 100, 100, 100),
            Element.Wind    to listOf(100, 100, 100, 100),
            Element.Earth   to listOf(100, 100, 100, 100),
            Element.Poison  to listOf(125, 125, 125, 125),
            Element.Holy    to listOf(0, 0, 0, 0),
            Element.Dark    to listOf(200, 200, 200, 200),
            Element.Ghost   to listOf(100, 100, 100, 100),
            Element.Undead  to listOf(200, 200, 200, 200)
        ),

        Element.Dark to mapOf(
            Element.Neutral to listOf(100, 100, 100, 100),
            Element.Fire    to listOf(100, 100, 100, 100),
            Element.Water   to listOf(100, 100, 100, 100),
            Element.Wind    to listOf(100, 100, 100, 100),
            Element.Earth   to listOf(100, 100, 100, 100),
            Element.Poison  to listOf(125, 125, 125, 125),
            Element.Holy    to listOf(50, 25, 0, 0),
            Element.Dark    to listOf(0, 0, 0, 0),
            Element.Ghost   to listOf(100, 100, 100, 100),
            Element.Undead  to listOf(50, 25, 0, 0)
        ),

        Element.Ghost to mapOf(
            Element.Neutral to listOf(125, 150, 175, 200),
            Element.Fire    to listOf(100, 100, 100, 100),
            Element.Water   to listOf(100, 100, 100, 100),
            Element.Wind    to listOf(100, 100, 100, 100),
            Element.Earth   to listOf(100, 100, 100, 100),
            Element.Poison  to listOf(100, 100, 100, 100),
            Element.Holy    to listOf(100, 100, 100, 100),
            Element.Dark    to listOf(100, 100, 100, 100),
            Element.Ghost   to listOf(0, 0, 0, 0),
            Element.Undead  to listOf(75, 50, 25, 0)
        ),

        Element.Undead to mapOf(
            Element.Neutral to listOf(100, 100, 100, 100),
            Element.Fire    to listOf(125, 150, 175, 200),
            Element.Water   to listOf(50, 25, 0, 0),
            Element.Wind    to listOf(100, 100, 100, 100),
            Element.Earth   to listOf(100, 100, 100, 100),
            Element.Poison  to listOf(0, 0, 0, 0),
            Element.Holy    to listOf(200, 200, 200, 200),
            Element.Dark    to listOf(25, 0, 0, 0),
            Element.Ghost   to listOf(50, 25, 0, 0),
            Element.Undead  to listOf(0, 0, 0, 0)
        )
    )

    /**
     * 取得傷害倍率（例如 1.0、0.25、2.0…）
     */
    fun getMultiplier(
        attack: Element,
        defense: Element,
        defenseLv: ElementLevel
    ): Double {
        val row = table[attack] ?: return 1.0
        val col = row[defense] ?: return 1.0
        val index = defenseLv.lv - 1
        return pct(col[index])
    }

    private fun sample() {
        val attackElement = Element.Fire
        val targetElement = Element.Water
        val targetElementLv = ElementLevel.Lv2

        val modifier = ElementModifier.getMultiplier(
            attackElement,
            targetElement,
            targetElementLv
        )
    }
}