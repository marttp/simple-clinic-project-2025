package dev.tpcoder.clinic.exception

class AppointmentNotFoundException(override val message: String) : RuntimeException(message)