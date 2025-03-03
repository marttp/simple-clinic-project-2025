package dev.tpcoder.clinic.controller

import dev.tpcoder.clinic.model.Doctor
import dev.tpcoder.clinic.service.DoctorService
import dev.tpcoder.clinic.service.external.PharmacyService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/internal")
class InternalController(
    private val doctorService: DoctorService,
    private val pharmacyService: PharmacyService
) {

    @GetMapping("/doctors")
    fun getDoctors(): List<Doctor> =
        doctorService.getDoctors()

    @PostMapping("/doctors")
    @ResponseStatus(HttpStatus.CREATED)
    fun addDoctor(@RequestBody doctor: Doctor) =
        doctorService.addDoctor(doctor)

    @GetMapping("/medicines")
    fun getListOfAvailableMedicines() =
        pharmacyService.getMedicines()
}