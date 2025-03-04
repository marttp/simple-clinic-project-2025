package dev.tpcoder

import dev.tpcoder.model.DiagnosisInfo
import dev.tpcoder.service.DiagnosisService
import io.github.damir.denis.tudor.ktor.server.rabbitmq.RabbitMQ
import io.github.damir.denis.tudor.ktor.server.rabbitmq.dsl.basicConsume
import io.github.damir.denis.tudor.ktor.server.rabbitmq.dsl.rabbitmq
import io.github.damir.denis.tudor.ktor.server.rabbitmq.rabbitMQ
import io.ktor.server.application.*
import kotlinx.coroutines.Dispatchers

fun Application.configureRabbitMQ() {

    val diagnosisService = DiagnosisService()

    val rabbitMQHost = environment.config.property("service.rabbitmq.host").getString()
    val rabbitMQPort = environment.config.property("service.rabbitmq.port").getString()
    val rabbitMQUsername = environment.config.property("service.rabbitmq.username").getString()
    val rabbitMQPassword = environment.config.property("service.rabbitmq.password").getString()
    val rabbitMQUri = "amqp://$rabbitMQUsername:$rabbitMQPassword@$rabbitMQHost:$rabbitMQPort"
    val queueName = environment.config.property("service.rabbitmq.queue-name").getString()

    install(RabbitMQ) {
        uri = rabbitMQUri
        defaultConnectionName = "pharmacy-connection"
        dispatcherThreadPollSize = 4
        tlsEnabled = false
    }

    rabbitmq {
        basicConsume {
            autoAck = true
            queue = queueName
            dispatcher = Dispatchers.rabbitMQ
            coroutinePollSize = 100
            deliverCallback<DiagnosisInfo> { _, message ->
                diagnosisService.process(message)
            }
        }
    }
}
