package com.ms.user.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BrokerConfig {
    @Value("${broker.queues.createUser.name}")
    private String createUserProfileQueueName;

    @Value("${broker.queues.updateUser.name}")
    private String updateUserProfileQueueName;

    @Value("${broker.exchanges.userCreated.name}")
    private String userCreatedExchangeName;

    @Value("${broker.exchanges.userUpdated.name}")
    private String userUpdatedExchangeName;


    @Bean
    public Queue createUserProfileQueue() {
        return new Queue(createUserProfileQueueName, true);
    }

    @Bean
    public Queue updateUserProfileQueue() {
        return new Queue(updateUserProfileQueueName, true);
    }

    @Bean
    public FanoutExchange userCreatedExchange() {
        return new FanoutExchange(userCreatedExchangeName, true, false);
    }

    @Bean
    public FanoutExchange userUpdatedExchange() {
        return new FanoutExchange(userUpdatedExchangeName, true, false);
    }

    @Bean
    public Binding userCreatedBiding(FanoutExchange userCreatedExchange, Queue createUserProfileQueue) {
        return BindingBuilder.bind(createUserProfileQueue).to(userCreatedExchange);
    }

    @Bean
    public Binding userUpdatedBiding(FanoutExchange userUpdatedExchange, Queue updateUserProfileQueue) {
        return BindingBuilder.bind(updateUserProfileQueue).to(userUpdatedExchange);
    }

    @Bean
    public JacksonJsonMessageConverter messageConverter() {
        return new JacksonJsonMessageConverter();
    }
}
