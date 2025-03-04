package dev.tpcoder.model

import kotlinx.serialization.Serializable

@Serializable
data class DiagnosisInfo(
    val appointmentId: Long,
    val patientFirstName: String,
    val patientLastName: String,
    val information: String,
    val medicinesRecommended: List<Medicine>
)
