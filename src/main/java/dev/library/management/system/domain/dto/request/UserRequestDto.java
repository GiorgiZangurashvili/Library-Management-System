package dev.library.management.system.domain.dto.request;

import jakarta.validation.constraints.NotBlank;

public record UserRequestDto(
        @NotBlank(message = "User's username should not be null or empty")
        String username,
        @NotBlank(message = "User's password should not be null or empty")
        String password
) {}
