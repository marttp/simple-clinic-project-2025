package dev.tpcoder.clinic.service

import dev.tpcoder.clinic.model.Doctor
import dev.tpcoder.clinic.repository.DoctorRepository
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class DoctorService(private val doctorRepository: DoctorRepository) {

    fun getDoctorByDoctorId(doctorId: Long): Optional<Doctor> = doctorRepository.findOneById(doctorId)

    fun getDoctors(): List<Doctor> = doctorRepository.findAll()

    fun addDoctor(doctor: Doctor): Doctor = doctorRepository.save(doctor)
}