package dev.library.management.system.service.interfaces;

import dev.library.management.system.domain.dto.response.BorrowingHistoryResponseDto;
import dev.library.management.system.exception.badrequest.BookIsAlreadyBorrowedException;
import dev.library.management.system.exception.notfound.EntityNotFoundException;

import java.util.List;

public interface BorrowingHistoryService {

    /**
     * Retrieves the entire borrowing history.
     *
     * @return A list of BorrowingHistoryResponseDto objects representing all borrowing history entries.
     */
    List<BorrowingHistoryResponseDto> getBorrowingHistory();

    /**
     * Retrieves the borrowing history for a specific user.
     *
     * @param userId The ID of the user whose borrowing history is to be retrieved.
     * @return A list of BorrowingHistoryResponseDto objects representing the user's borrowing history.
     * @throws EntityNotFoundException If no user is found with the given ID.
     */
    List<BorrowingHistoryResponseDto> getBorrowingHistoryOfUser(long userId) throws EntityNotFoundException;

    /**
     * Retrieves the borrowing history for a specific book.
     *
     * @param bookId The ID of the book whose borrowing history is to be retrieved.
     * @return A list of BorrowingHistoryResponseDto objects representing the book's borrowing history.
     * @throws EntityNotFoundException If no book is found with the given ID.
     */
    List<BorrowingHistoryResponseDto> getBorrowingHistoryOfBook(long bookId) throws EntityNotFoundException;

    /**
     * Records a new book borrowing.
     *
     * @param userId The ID of the user borrowing the book.
     * @param bookId The ID of the book being borrowed.
     * @return A BorrowingHistoryResponseDto object representing the new borrowing record.
     * @throws EntityNotFoundException If no user or book is found with the given IDs.
     * @throws BookIsAlreadyBorrowedException If the book is already borrowed.
     */
    BorrowingHistoryResponseDto borrowBook(long userId, long bookId) throws EntityNotFoundException, BookIsAlreadyBorrowedException;

    /**
     * Records the return of a borrowed book.
     *
     * @param borrowingHistoryId The ID of the borrowing history entry to be updated.
     * @return A BorrowingHistoryResponseDto object representing the updated borrowing record.
     * @throws EntityNotFoundException If no borrowing history entry is found with the given ID.
     */
    BorrowingHistoryResponseDto returnBook(long borrowingHistoryId) throws EntityNotFoundException;

}
