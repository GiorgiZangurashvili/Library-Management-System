package dev.library.management.system.domain.dto.request;

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
    private String title;
    private long authorId;
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
