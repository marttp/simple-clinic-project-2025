package dev.tpcoder.clinic.model

import java.time.LocalDateTime

data class Appointment(
    val id: Long,
    val date: LocalDateTime,
    val doctorId: Long
)
