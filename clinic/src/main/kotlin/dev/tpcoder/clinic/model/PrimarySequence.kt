package dev.tpcoder.clinic.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class PrimarySequence(
    @Id
    var id: String? = null,
    var seq: Long? = null
)
