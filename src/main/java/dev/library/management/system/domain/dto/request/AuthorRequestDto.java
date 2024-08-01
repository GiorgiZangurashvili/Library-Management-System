package dev.library.management.system.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthorRequestDto {
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String biography;

    @Override
    public String toString() {
        return "AuthorRequestDto{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", biography='" + biography + '\'' +
                '}';
    }
}
