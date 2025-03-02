package dev.tpcoder.clinic.controller

import dev.tpcoder.clinic.exception.AppointmentNotFoundException
import dev.tpcoder.clinic.exception.DoctorUnavailableException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest

@ControllerAdvice
class DefinedControllerAdvice {

    @ExceptionHandler(DoctorUnavailableException::class)
    fun handleDoctorUnavailableException(
        ex: DoctorUnavailableException,
        request: WebRequest
    ): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(
            message = ex.message,
            details = request.getDescription(false)
        )
        return ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(AppointmentNotFoundException::class)
    fun handleAppointmentNotFoundException(
        ex: AppointmentNotFoundException,
        request: WebRequest
    ): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(
            message = ex.message,
            details = request.getDescription(false)
        )
        return ResponseEntity(errorResponse, HttpStatus.NOT_FOUND)
    }

    data class ErrorResponse(
        val message: String?,
        val details: String
    )
}