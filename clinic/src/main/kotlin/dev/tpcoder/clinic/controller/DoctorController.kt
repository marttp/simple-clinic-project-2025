package dev.tpcoder.clinic.controller

import dev.tpcoder.clinic.model.Doctor
import dev.tpcoder.clinic.service.DoctorService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/doctors")
class DoctorController(
    private val doctorService: DoctorService,
) {

    @GetMapping
    fun getDoctors(): List<Doctor> =
        doctorService.getDoctors()

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun addDoctor(@RequestBody doctor: Doctor) =
        doctorService.addDoctor(doctor)
}