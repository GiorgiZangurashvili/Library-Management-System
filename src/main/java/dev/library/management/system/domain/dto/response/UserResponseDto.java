package dev.library.management.system.domain.dto.response;

import java.io.Serializable;

public record UserResponseDto(
        long id,
        String username,
        boolean isDisabled
) implements Serializable {}
