package dev.library.management.system.domain.dto.response;

import org.springframework.http.HttpStatus;

public record ExceptionResponseDto(
        int statusCode,
        HttpStatus httpStatus,
        String errorMessage
) {}
