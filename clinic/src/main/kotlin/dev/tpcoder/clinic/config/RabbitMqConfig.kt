package dev.tpcoder.clinic.config

import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.DirectExchange
import org.springframework.amqp.core.Queue
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RabbitMqConfig {

    @Bean
    fun diagnosisExchange(): DirectExchange {
        return DirectExchange(DIAGNOSIS_EXCHANGE_NAME)
    }

    @Bean
    fun diagnosisQueue(): Queue {
        return Queue(DIAGNOSIS_QUEUE_NAME)
    }

    @Bean
    fun diagnosisInfoBinding(): Binding {
        return BindingBuilder.bind(diagnosisQueue())
            .to(diagnosisExchange())
            .with(DIAGNOSIS_BINDING_NAME)
    }


    @Bean
    fun jacksonConverter(): Jackson2JsonMessageConverter {
        return Jackson2JsonMessageConverter()
    }

    @Bean
    fun rabbitTemplate(connectionFactory: ConnectionFactory): RabbitTemplate {
        val rabbitTemplate = RabbitTemplate(connectionFactory)
        rabbitTemplate.messageConverter = jacksonConverter()
        return rabbitTemplate
    }

    companion object {
        const val DIAGNOSIS_EXCHANGE_NAME = "diagnosis-exchange"
        const val DIAGNOSIS_QUEUE_NAME = "diagnosis-queue"
        const val DIAGNOSIS_BINDING_NAME = "clinic-diagnosis"
    }
}