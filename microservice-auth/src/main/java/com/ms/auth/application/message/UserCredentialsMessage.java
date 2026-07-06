package com.ms.auth.application.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.UUID;

public record UserCredentialsMessage(
        @NotNull UUID id,
        @NotBlank String username,
        @Email String email
) implements Serializable {
}
