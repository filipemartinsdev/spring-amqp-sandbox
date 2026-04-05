package com.ms.user.application.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record UserProfileResponse(
        UUID id,
        String username,
        String email,
        String bio
) {
}
