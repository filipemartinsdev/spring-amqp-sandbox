package com.ms.auth.infra.broker;

import com.ms.auth.application.message.UserCredentialsMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RabbitMQProducer {
    @Value("${broker.exchanges.userCreated.name}")
    public String userCreatedExchangeName;

    @Value("${broker.exchanges.userUpdated.name}")
    public String userUpdatedExchangeName;

    private final RabbitTemplate rabbitTemplate;

    public RabbitMQProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publishUserCredentialsCreated(UserCredentialsMessage userCredentials){
        rabbitTemplate.convertAndSend(userCreatedExchangeName, "", userCredentials);
        log.info("Published user credentials created: {}", userCredentials);
    }

    public void publishUserCredentialsUpdated(UserCredentialsMessage userCredentials){
        rabbitTemplate.convertAndSend(userUpdatedExchangeName, "", userCredentials);
        log.info("Published user credentials updated: {}", userCredentials);
    }
}
