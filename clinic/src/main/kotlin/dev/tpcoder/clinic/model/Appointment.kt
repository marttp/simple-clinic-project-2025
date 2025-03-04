package dev.tpcoder.clinic.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document
data class Appointment(
    @Id
    var id: Long? = null,
    val date: LocalDateTime,
    val doctorId: Long
)
