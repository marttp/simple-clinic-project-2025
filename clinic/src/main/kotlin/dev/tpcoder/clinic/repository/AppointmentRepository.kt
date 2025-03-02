package dev.tpcoder.clinic.repository

import dev.tpcoder.clinic.model.Appointment
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import java.time.LocalDateTime

interface AppointmentRepository : MongoRepository<Appointment, Long> {

    @Query("{'date': {\$gte: ?0, \$lt: ?1}}")
    fun findAppointmentByTimeRange(from: LocalDateTime, to: LocalDateTime): List<Appointment>
}