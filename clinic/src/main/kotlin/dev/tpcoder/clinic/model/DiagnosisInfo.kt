package dev.tpcoder.clinic.model

data class DiagnosisInfo(
    val appointmentId: Long,
    val doctorId: Long,
    val patientFirstName: String,
    val patientLastName: String,
    val information: String,
    val medicinesRecommended: List<Medicine>
)
