package com.ms.auth.infra.broker;

import com.ms.auth.application.dto.UserCredentialsDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RabbitMQProducer {
    @Value("${broker.queues.userCreated.name}")
    public String userCreatedQueueName;

    @Value("${broker.queues.userUpdated.name}")
    public String userUpdatedQueueName;

    private final RabbitTemplate rabbitTemplate;

    public RabbitMQProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publishUserCredentialsCreated(UserCredentialsDTO userCredentials){
        rabbitTemplate.convertAndSend("", userCreatedQueueName, userCredentials);
        log.info("Published user credentials created: {}", userCredentials);
    }

    public void publishUserCredentialsUpdated(UserCredentialsDTO userCredentials){
        rabbitTemplate.convertAndSend("", userUpdatedQueueName, userCredentials);
        log.info("Published user credentials updated: {}", userCredentials);
    }
}
