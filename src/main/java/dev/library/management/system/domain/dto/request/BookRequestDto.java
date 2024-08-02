package dev.library.management.system.domain.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookRequestDto {
    @NotBlank(message = "title of book should not be null or empty")
    private String title;
    @NotNull(message = "authorId of book should not be null")
    @Positive(message = "authorId of book should be positive")
    private Long authorId;
    private List<Long> categoryIds;

    @Override
    public String toString() {
        return "BookRequestDto{" +
                "title='" + title + '\'' +
                ", authorId=" + authorId +
                ", categoryIds=" + categoryIds +
                '}';
    }
}
