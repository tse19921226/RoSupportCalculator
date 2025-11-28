package com.elvis_c.sharedcalculaotr.engine

import kotlinx.serialization.Serializable

/**
 * RO 屬性階級 1～4
 */
@Serializable
enum class ElementLevel(val lv: Int) {
    Lv1(1), Lv2(2), Lv3(3), Lv4(4)
}