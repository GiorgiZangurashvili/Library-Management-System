package dev.library.management.system.domain.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record AuthorRequestDto(
        @NotBlank(message = "firstName of author should not be null or empty")
        String firstName,

        @NotBlank(message = "lastName of author should not be null or empty")
        String lastName,

        @NotNull(message = "birthDate of author should not be null")
        LocalDate birthDate,

        String biography
) {}
