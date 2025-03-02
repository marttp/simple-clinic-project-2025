package dev.tpcoder.clinic.service

import dev.tpcoder.clinic.model.PrimarySequence
import org.springframework.data.mongodb.core.FindAndModifyOptions
import org.springframework.data.mongodb.core.MongoOperations
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Update
import org.springframework.stereotype.Service


@Service
class SequenceService(private val mongoOperations: MongoOperations) {

    fun getNextValue(): Long {
        val query = Query(Criteria.where("_id").`is`(APPOINTMENT_SEQUENCE))
        val update = Update().inc("seq", 1)
        val options = FindAndModifyOptions()
            .returnNew(true)
            .upsert(true)

        val primarySequence = mongoOperations.findAndModify(query, update, options, PrimarySequence::class.java)
            ?: PrimarySequence().apply {
                id = APPOINTMENT_SEQUENCE
                seq = 10000
                mongoOperations.insert(this)
            }

        return primarySequence.seq!!
    }

    companion object {
        const val APPOINTMENT_SEQUENCE: String = "appointmentSequence"
    }
}