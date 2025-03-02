package dev.tpcoder.clinic.exception

class DoctorUnavailableException(override val message: String) : RuntimeException(message)