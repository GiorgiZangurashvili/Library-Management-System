package dev.library.management.system.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BorrowingHistoryResponseDto {
    private long id;
    private LocalDateTime borrowedDate;
    private LocalDateTime returnedDate;
    private long borrowedBookId;
    private long borrowerId;
}
