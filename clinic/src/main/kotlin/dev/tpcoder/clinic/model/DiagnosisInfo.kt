package dev.tpcoder.clinic.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class DiagnosisInfo(
    @Id
    val appointmentId: Long,
    val patientFirstName: String,
    val patientLastName: String,
    val information: String,
    val medicinesRecommended: List<Medicine>
)
