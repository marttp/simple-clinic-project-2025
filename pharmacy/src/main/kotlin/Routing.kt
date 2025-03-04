package dev.tpcoder

import dev.tpcoder.model.Medicine
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        get("/v1/medicines") {
            val medicines = listOf(
                Medicine("Paracetamol", "Analgesic and Antipyretic"),
                Medicine("Ibuprofen", "NSAID"),
                Medicine("Amoxicillin", "Antibiotic"),
                Medicine("Metformin", "Antidiabetic"),
                Medicine("Lisinopril", "Antihypertensive"),
                Medicine("Atorvastatin", "Cholesterol-lowering"),
                Medicine("Omeprazole", "Proton Pump Inhibitor"),
                Medicine("Cetirizine", "Antihistamine")
            )
            call.respond(medicines)
        }
    }
}
