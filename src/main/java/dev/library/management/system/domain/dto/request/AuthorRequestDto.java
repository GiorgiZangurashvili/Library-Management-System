package dev.library.management.system.domain.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotBlank(message = "firstName of author should not be null or empty")
    private String firstName;
    @NotBlank(message = "lastName of author should not be null or empty")
    private String lastName;
    @NotNull(message = "birthDate of author should not be null")
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
