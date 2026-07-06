package com.ms.auth.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class BrokerConfig {
    @Value("${broker.exchanges.userCreated.name}")
    private String userCreatedExchangeName;

    @Value("${broker.exchanges.userUpdated.name}")
    private String userUpdatedExchangeName;

    @Bean
    public FanoutExchange userCreatedExchange() {
        return new FanoutExchange(userCreatedExchangeName, true, false);
    }

    @Bean
    public FanoutExchange userUpdatedExchange() {
        return new FanoutExchange(userUpdatedExchangeName, true, false);
    }

    @Bean
    public JacksonJsonMessageConverter messageConverter() {
        return new JacksonJsonMessageConverter();
    }
}

