package dev.tpcoder.clinic.repository

import dev.tpcoder.clinic.model.Doctor
import org.springframework.data.mongodb.repository.MongoRepository

interface DoctorRepository : MongoRepository<Doctor, Long> {
}