package dev.tpcoder

import kotlinx.serialization.Serializable

@Serializable
data class Medicine(
    val name: String,
    val category: String
)
