package dev.library.management.system.domain.dto.request;

public record UpdateUserRequestDto(
        String newUsername,
        String newPassword
) {}
