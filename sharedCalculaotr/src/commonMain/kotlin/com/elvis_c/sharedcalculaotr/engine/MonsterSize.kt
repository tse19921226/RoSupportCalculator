package com.elvis_c.sharedcalculaotr.engine

import kotlinx.serialization.Serializable

/**
 * RO 體型：小 / 中 / 大
 */
@Serializable
enum class MonsterSize {
    Small,
    Medium,
    Large
}