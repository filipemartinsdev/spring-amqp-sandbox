package com.ms.auth.application.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.Optional;

public record UpdateUserCredentialsRequest(
        Optional<@NotBlank String> username,
        Optional<@Email String> email
) {
}
