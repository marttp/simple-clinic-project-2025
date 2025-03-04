package dev.tpcoder.clinic.repository

import dev.tpcoder.clinic.model.DiagnosisInfo
import org.springframework.data.mongodb.repository.MongoRepository

interface DiagnosisInfoRepository : MongoRepository<DiagnosisInfo, Long>