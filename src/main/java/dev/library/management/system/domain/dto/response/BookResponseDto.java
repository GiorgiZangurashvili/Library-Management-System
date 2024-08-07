package dev.library.management.system.domain.dto.response;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public record BookResponseDto(
        long id,
        String title,
        boolean isBorrowed,
        AuthorResponseDto author,
        List<CategoryResponseDto> categories
) implements Serializable {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookResponseDto that = (BookResponseDto) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "BookResponseDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", isBorrowed=" + isBorrowed +
                ", author=" + author +
                ", categories=" + categories +
                '}';
    }
}
