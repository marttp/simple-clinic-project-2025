package dev.tpcoder.clinic.service

import dev.tpcoder.clinic.exception.AppointmentNotFoundException
import dev.tpcoder.clinic.exception.DoctorUnavailableException
import dev.tpcoder.clinic.model.Appointment
import dev.tpcoder.clinic.model.DiagnosisInfo
import dev.tpcoder.clinic.model.dto.AppointmentInfoDto
import dev.tpcoder.clinic.model.dto.CreateAppointment
import dev.tpcoder.clinic.repository.AppointmentRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class AppointmentService(
    private val doctorService: DoctorService,
    private val appointmentRepository: AppointmentRepository
) {

    private lateinit var localCacheDoctors: Set<Long>

    init {
        // Small Clinic => No-Cache or Local Cache
        // Large/Multi-region Clinic => Distributed Cache option
        // 0. Store doctor to cache
        val doctors = doctorService.getDoctors()
        localCacheDoctors = doctors.map { it.id }.toSet()
    }

    fun getAppointmentById(id: Long): AppointmentInfoDto {
        // Data is in database
        // If no => Error
        val existingAppointment = appointmentRepository.findById(id)
            .orElseThrow { AppointmentNotFoundException("Appointment with id $id not found") }
        val doctor = doctorService.getDoctorByDoctorId(existingAppointment.doctorId)
            .orElseThrow { IllegalArgumentException("Doctor with id ${existingAppointment.doctorId} not found") }
        return AppointmentInfoDto(
            id = id,
            date = existingAppointment.date,
            doctorName = "${doctor.firstName} ${doctor.lastName}",
        )
    }

    // Assume => Clinic open every day
    // Reservation => Assume Input => start of range => 1 hour
    fun createAppointment(body: CreateAppointment): Appointment {
        // 1. Get existing appointment in current time range
        val startRange = body.date
        val endRange = body.date.plusHours(1)
        val appointmentInRange = appointmentRepository
            .findAppointmentByTimeRange(startRange, endRange)
        // 2. Filter doctor
        val busyDoctors = appointmentInRange.map { it.doctorId }.toSet()
        val availableDoctors = localCacheDoctors - busyDoctors
        // If no one available => Bad Request
        if (availableDoctors.isEmpty()) {
            throw DoctorUnavailableException("Our doctors quite busy 😂")
        }
        // Same doctor cannot reserve on same time
        val doctor = availableDoctors.random()
        // 3. Store to DB
        val prepareAppointment = Appointment(
            date = body.date,
            doctorId = doctor,
        )
        val appointment = appointmentRepository.save(prepareAppointment)
        logger.debug("Created appointment {}", appointment)
        return appointment
    }

    fun updateAppointment(info: DiagnosisInfo): DiagnosisInfo {
        // Just insert record on database
        // Send message to Pharmacy
        return DiagnosisInfo(
            appointmentId = 0L,
            doctorId = 0L,
            patientFirstName = "patientFirstName",
            patientLastName = "patientLastName",
            information = "",
            medicinesRecommended = listOf()
        )
    }

    companion object {
        val logger: Logger = LoggerFactory.getLogger(AppointmentService::class.java)
    }
}