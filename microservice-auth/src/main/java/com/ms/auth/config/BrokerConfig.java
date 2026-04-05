package com.ms.auth.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class BrokerConfig {
    @Value("${broker.queues.userCreated.name}")
    private String userCreatedQueueName;

    @Value("${broker.queues.userUpdated.name}")
    private String userUpdatedQueueName;

    @Bean
    public Queue userCreatedQueue() {
        return new Queue(userCreatedQueueName, true);
    }

    @Bean
    public Queue userUpdatedQueue() {
        return new Queue(userUpdatedQueueName, true);
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        ObjectMapper objectMapper = new ObjectMapper();
        return new Jackson2JsonMessageConverter(objectMapper);
    }
}

