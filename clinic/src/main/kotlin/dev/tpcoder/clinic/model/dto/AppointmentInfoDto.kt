package dev.tpcoder.clinic.model.dto

import java.time.LocalDateTime

data class AppointmentInfoDto(
    var id: Long,
    val date: LocalDateTime,
    val doctorName: String
)
