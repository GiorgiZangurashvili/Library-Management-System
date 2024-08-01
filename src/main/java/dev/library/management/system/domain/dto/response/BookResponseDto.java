package dev.library.management.system.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookResponseDto {
    private long id;
    private String title;
    private boolean isBorrowed;
    private AuthorResponseDto author;
    private List<CategoryResponseDto> categories;

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
