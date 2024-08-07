package dev.library.management.system.domain.dto.response;

import dev.library.management.system.domain.enums.Genre;

import java.io.Serializable;

public record CategoryResponseDto(
        long id,
        Genre genre
) implements Serializable {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryResponseDto that = (CategoryResponseDto) o;
        return id == that.id && genre == that.genre;
    }

    @Override
    public String toString() {
        return "CategoryResponseDto{" +
                "id=" + id +
                ", genre=" + genre +
                '}';
    }
}
