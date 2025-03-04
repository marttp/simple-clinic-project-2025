package dev.tpcoder.clinic.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Doctor(
    @Id
    val id: Long,
    val firstName: String,
    val lastName: String
)
