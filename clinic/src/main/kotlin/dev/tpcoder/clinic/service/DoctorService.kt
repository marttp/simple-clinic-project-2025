package dev.tpcoder.clinic.service

import dev.tpcoder.clinic.model.Doctor
import dev.tpcoder.clinic.repository.DoctorRepository
import org.springframework.stereotype.Service

@Service
class DoctorService(private val doctorRepository: DoctorRepository) {

    fun getDoctors(): List<Doctor> = doctorRepository.findAll()

    fun addDoctor(doctor: Doctor): Doctor = doctorRepository.save(doctor)
}