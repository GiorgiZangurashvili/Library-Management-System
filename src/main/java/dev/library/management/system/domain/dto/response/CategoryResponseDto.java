package dev.library.management.system.domain.dto.response;

import dev.library.management.system.domain.enums.Genre;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategoryResponseDto {
    private long id;
    private Genre genre;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryResponseDto that = (CategoryResponseDto) o;
        return id == that.id && genre == that.genre;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, genre);
    }

    @Override
    public String toString() {
        return "CategoryResponseDto{" +
                "id=" + id +
                ", genre=" + genre +
                '}';
    }
}
