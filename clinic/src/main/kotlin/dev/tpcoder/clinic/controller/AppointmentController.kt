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

    @PostMapping
    fun createAppointment(@RequestBody body: CreateAppointment): Appointment =
        appointmentService.createAppointment(body)

    @PatchMapping
    fun diagnosisCompleted(@RequestBody body: DiagnosisInfo): String {
        appointmentService.updateAppointment(body)
        return "SUCCESS"
    }
}