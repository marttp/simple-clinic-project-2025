package dev.tpcoder.model

import kotlinx.serialization.Serializable

@Serializable
data class Medicine(
    val name: String,
    val category: String
)
