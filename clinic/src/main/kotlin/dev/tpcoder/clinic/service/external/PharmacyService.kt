package dev.tpcoder.clinic.service.external

import dev.tpcoder.clinic.model.Medicine
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.ParameterizedTypeReference
import org.springframework.stereotype.Service
import org.springframework.web.client.RestClient

@Service
class PharmacyService(
    @Value("\${external.pharmacy.url}") private val baseUrl: String,
) {

    private val webClient: RestClient= RestClient.builder()
        .baseUrl(baseUrl)
        .build()

    fun getMedicines(): List<Medicine> {
        return webClient.get()
            .uri("/v1/medicines")
            .retrieve()
            .body(object : ParameterizedTypeReference<List<Medicine>>() {})!!
    }
}