package dev.tpcoder.clinic.controller

import dev.tpcoder.clinic.model.Appointment
import dev.tpcoder.clinic.model.DiagnosisInfo
import dev.tpcoder.clinic.model.dto.CreateAppointment
import dev.tpcoder.clinic.service.AppointmentService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/appointments")
class AppointmentController(
    private val appointmentService: AppointmentService
) {

    @GetMapping("/{id}")
    fun getAppointment(@PathVariable("id") id: Long): Appointment =
        appointmentService.getAppointmentById(id)

    // Assume Input => 9AM - 5PM
    // Date => always 00 seconds and hour round
    // e.g. 2025-03-02T09:00:00, 2025-03-02T10:00:00, 2025-03-02T11:00:00
    // UTC Timezone
    @PostMapping
    fun createAppointment(@RequestBody body: CreateAppointment): Appointment =
        appointmentService.createAppointment(body)

    @PatchMapping
    fun diagnosisCompleted(@RequestBody body: DiagnosisInfo): String {
        appointmentService.updateAppointment(body)
        return "SUCCESS"
    }
}