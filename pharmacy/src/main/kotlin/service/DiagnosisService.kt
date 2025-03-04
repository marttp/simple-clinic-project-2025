package dev.tpcoder.service

import dev.tpcoder.model.DiagnosisInfo
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class DiagnosisService {

    fun process(diagnosisInfo: DiagnosisInfo) {
        log.info("Received message: $diagnosisInfo")
    }

    companion object {
        val log: Logger = LoggerFactory.getLogger(DiagnosisService::class.java)
    }
}