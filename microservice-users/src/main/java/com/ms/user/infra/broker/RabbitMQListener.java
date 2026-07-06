package com.ms.user.infra.broker;

import com.ms.user.application.dto.UserCredentialsDTO;
import com.ms.user.services.UserProfileService;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQListener {
    private final UserProfileService userProfileService;

    public RabbitMQListener(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @RabbitListener(
            queues = "${broker.queues.createUser.name}"
    )
    public void consumeUserCreated(UserCredentialsDTO userCredentialsDTO) {
        try {
            userProfileService.createByUserCredentials(userCredentialsDTO);
        } catch (Exception e) {
            throw new AmqpRejectAndDontRequeueException(e);
        }
    }

    @RabbitListener(
            queues = "${broker.queues.updateUser.name}"
    )
    public void consumeUserUpdated(UserCredentialsDTO userCredentialsDTO) {
        try {
            userProfileService.updateFromCredentials(userCredentialsDTO);
        } catch (Exception e){
            throw new AmqpRejectAndDontRequeueException(e);
        }
    }
}
