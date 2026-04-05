package com.ms.auth.services;

import com.ms.auth.application.dto.CreateUserRequest;
import com.ms.auth.application.dto.UpdateUserCredentialsRequest;
import com.ms.auth.application.dto.UserCredentialsDTO;
import com.ms.auth.domain.UserCredentials;
import com.ms.auth.infra.broker.RabbitMQProducer;
import com.ms.auth.infra.persistence.UserCredentialsRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserCredentialsService {
    private final UserCredentialsRepository userCredentialsRepository;
    private final RabbitMQProducer rabbitMQProducer;

    public UserCredentialsService(UserCredentialsRepository userCredentialsRepository, RabbitMQProducer rabbitMQProducer) {
        this.userCredentialsRepository = userCredentialsRepository;
        this.rabbitMQProducer = rabbitMQProducer;
    }

    @Transactional
    public void register(CreateUserRequest request){
        UserCredentials newUser = new UserCredentials();
        newUser.setUsername(request.username());
        newUser.setEmail(request.email());
        newUser.setPassword(request.password());

        var createdUser = userCredentialsRepository.save(newUser);

        UserCredentialsDTO userCredentialsDTO = new UserCredentialsDTO(
                createdUser.getId(),
                createdUser.getUsername(),
                createdUser.getEmail()
        );

        rabbitMQProducer.publishUserCredentialsCreated(userCredentialsDTO);
    }

    public List<UserCredentialsDTO> getAll() {
        return userCredentialsRepository.findAll().stream()
                .map(userCredentials ->
                    new UserCredentialsDTO(
                            userCredentials.getId(),
                            userCredentials.getUsername(),
                            userCredentials.getEmail()
                    )
                )
                .toList();
    }

    public UserCredentialsDTO update(UUID userId, @Valid UpdateUserCredentialsRequest request) {
        var userCredentials = userCredentialsRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (request.username().isPresent()) {
            userCredentials.setUsername(request.username().get());
        }

        if (request.email().isPresent()) {
            userCredentials.setEmail(request.email().get());
        }

        var newUserCredentials = userCredentialsRepository.save(userCredentials);

        var response = new UserCredentialsDTO(
                newUserCredentials.getId(),
                newUserCredentials.getUsername(),
                newUserCredentials.getEmail()
        );

        rabbitMQProducer.publishUserCredentialsUpdated(response);

        return response;
    }
}
