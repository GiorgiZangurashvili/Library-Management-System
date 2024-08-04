package dev.library.management.system.domain.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;

public record BookRequestDto(
        @NotBlank(message = "title of book should not be null or empty")
        String title,

        @NotNull(message = "authorId of book should not be null")
        @Positive(message = "authorId of book should be positive")
        Long authorId,

        List<Long> categoryIds
) {}
