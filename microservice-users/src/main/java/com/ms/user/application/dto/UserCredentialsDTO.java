package com.ms.user.application.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.UUID;

public record UserCredentialsDTO (
        @NotNull UUID id,
        @NotBlank String username,
        @Email String email
) implements Serializable {
}
