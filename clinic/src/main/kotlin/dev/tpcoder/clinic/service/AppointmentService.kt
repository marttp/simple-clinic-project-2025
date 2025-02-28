package dev.tpcoder.clinic.service

import dev.tpcoder.clinic.model.Appointment
import dev.tpcoder.clinic.model.DiagnosisInfo
import dev.tpcoder.clinic.model.dto.CreateAppointment
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class AppointmentService {

    fun getAppointmentById(id: Long): Appointment {
        return Appointment(
            id = id,
            date = LocalDateTime.now(),
            doctorId = 1L,
        )
    }

    fun createAppointment(body: CreateAppointment): Appointment {
        return Appointment(
            id = 0L,
            date = LocalDateTime.now(),
            doctorId = 1L,
        )
    }

    fun updateAppointment(info: DiagnosisInfo): DiagnosisInfo {
        return DiagnosisInfo(
            appointmentId = 0L,
            doctorId = 0L,
            patientFirstName = "patientFirstName",
            patientLastName = "patientLastName",
            information = "",
            medicinesRecommended = listOf()
        )
    }
}