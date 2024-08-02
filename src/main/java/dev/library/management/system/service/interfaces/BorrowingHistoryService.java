package dev.library.management.system.service.interfaces;

import dev.library.management.system.domain.dto.response.BorrowingHistoryResponseDto;

import java.util.List;

public interface BorrowingHistoryService {

    List<BorrowingHistoryResponseDto> getBorrowingHistory();
    List<BorrowingHistoryResponseDto> getBorrowingHistoryOfUser(long userId);
    List<BorrowingHistoryResponseDto> getBorrowingHistoryOfBook(long bookId);
    BorrowingHistoryResponseDto borrowBook(long userId, long bookId);
    BorrowingHistoryResponseDto returnBook(long borrowingHistoryId);

}
