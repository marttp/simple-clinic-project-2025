package dev.tpcoder.clinic.config

import dev.tpcoder.clinic.model.Appointment
import dev.tpcoder.clinic.service.SequenceService
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent
import org.springframework.stereotype.Component

@Component
class AppointmentListener(
    private val sequenceService: SequenceService
) : AbstractMongoEventListener<Appointment>() {

    override fun onBeforeConvert(event: BeforeConvertEvent<Appointment>) {
        if (event.source.id == null) {
            event.source.id = sequenceService.getNextValue()
        }
    }
}