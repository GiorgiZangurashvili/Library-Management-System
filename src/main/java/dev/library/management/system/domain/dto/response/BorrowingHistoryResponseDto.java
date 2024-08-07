package dev.library.management.system.domain.dto.response;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public record BorrowingHistoryResponseDto(
        long id,
        LocalDateTime borrowedDate,
        LocalDateTime returnedDate,
        long borrowedBookId,
        long borrowerId
) implements Serializable {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BorrowingHistoryResponseDto that = (BorrowingHistoryResponseDto) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "BorrowingHistoryResponseDto{" +
                "id=" + id +
                ", borrowedDate=" + borrowedDate +
                ", returnedDate=" + returnedDate +
                ", borrowedBookId=" + borrowedBookId +
                ", borrowerId=" + borrowerId +
                '}';
    }
}
