package dev.tpcoder.clinic.repository

import dev.tpcoder.clinic.model.Doctor
import org.springframework.data.mongodb.repository.MongoRepository
import java.util.Optional

interface DoctorRepository : MongoRepository<Doctor, Long> {

    fun findOneById(id: Long): Optional<Doctor>
}