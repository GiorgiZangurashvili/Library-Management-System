package dev.library.management.system.domain.dto.response;

import java.time.LocalDate;
import java.util.Objects;

public record AuthorResponseDto(
        long id,
        String firstName,
        String lastName,
        String biography,
        LocalDate birthDate
) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorResponseDto that = (AuthorResponseDto) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "AuthorResponseDto{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", biography='" + biography + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
