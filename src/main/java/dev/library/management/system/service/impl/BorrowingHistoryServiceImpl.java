package dev.library.management.system.service.impl;

import dev.library.management.system.domain.dto.response.BorrowingHistoryResponseDto;
import dev.library.management.system.domain.entity.Book;
import dev.library.management.system.domain.entity.BorrowingHistory;
import dev.library.management.system.domain.entity.User;
import dev.library.management.system.domain.enums.EntityName;
import dev.library.management.system.domain.mapper.BorrowingHistoryMapper;
import dev.library.management.system.exception.badrequest.BookIsAlreadyBorrowedException;
import dev.library.management.system.exception.notfound.EntityNotFoundException;
import dev.library.management.system.repository.BookRepository;
import dev.library.management.system.repository.BorrowingHistoryRepository;
import dev.library.management.system.repository.UserRepository;
import dev.library.management.system.service.interfaces.BorrowingHistoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class BorrowingHistoryServiceImpl implements BorrowingHistoryService {
    /* Repositories */
    private final BorrowingHistoryRepository borrowingHistoryRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    /* Mappers */
    private final BorrowingHistoryMapper borrowingHistoryMapper;

    @Override
    public List<BorrowingHistoryResponseDto> getBorrowingHistory() {
        log.info("*** getBorrowingHistory() method called ***");
        return borrowingHistoryRepository.findAll()
                .stream()
                .map(borrowingHistoryMapper::mapBorrowingHistoryToBorrowingHistoryResponseDto)
                .toList();
    }

    @Override
    public List<BorrowingHistoryResponseDto> getBorrowingHistoryOfUser(final long userId) {
        log.info("*** getBorrowingHistoryOfUser(long userId) method called ***");

        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            log.error("Error while retrieving user with id = {}, Reason: Not Found", userId);
            throw new EntityNotFoundException(EntityName.User, userId);
        }

        return borrowingHistoryRepository.findByBorrowerId(userId)
                .stream()
                .map(borrowingHistoryMapper::mapBorrowingHistoryToBorrowingHistoryResponseDto)
                .toList();
    }

    @Override
    public List<BorrowingHistoryResponseDto> getBorrowingHistoryOfBook(final long bookId) {
        log.info("*** getBorrowingHistoryOfBook(long id) method called ***");

        Optional<Book> bookOptional = bookRepository.findById(bookId);
        if (bookOptional.isEmpty()) {
            log.error("Error while retrieving book with id = {}, Reason: Not Found", bookId);
            throw new EntityNotFoundException(EntityName.Book, bookId);
        }

        return borrowingHistoryRepository.findByBorrowedBookId(bookId)
                .stream()
                .map(borrowingHistoryMapper::mapBorrowingHistoryToBorrowingHistoryResponseDto)
                .toList();
    }

    @Override
    @Transactional
    public BorrowingHistoryResponseDto borrowBook(final long userId, final long bookId) {
        log.info("*** borrowBook(long userId, long bookId) method called ***");
        BorrowingHistory borrowingHistory = new BorrowingHistory();
        borrowingHistory.setBorrowedDate(LocalDateTime.now());

        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            log.error("Error while retrieving user with id = {}, Reason: Not Found", userId);
            throw new EntityNotFoundException(EntityName.User, userId);
        }
        borrowingHistory.setBorrowingUser(userOptional.get());

        Optional<Book> bookOptional = bookRepository.findById(bookId);
        if (bookOptional.isEmpty()) {
            log.error("Error while retrieving book with id = {}, Reason: Not Found", bookId);
            throw new EntityNotFoundException(EntityName.Book, bookId);
        }
        Book book = bookOptional.get();

        if (book.isBorrowed()) {
            log.error("Error while borrowing book with id = {}, Reason: Book is already borrowed", bookId);
            throw new BookIsAlreadyBorrowedException(book.getId());
        }

        book.setBorrowed(true);

        borrowingHistory.setBorrowedBook(book);

        borrowingHistoryRepository.save(borrowingHistory);
        log.info("Saved a new borrowingHistory");

        return borrowingHistoryMapper
                .mapBorrowingHistoryToBorrowingHistoryResponseDto(borrowingHistory);
    }

    @Override
    @Transactional
    public BorrowingHistoryResponseDto returnBook(final long borrowingHistoryId) {
        log.info("*** returnBook(long userId, long bookId) method called ***");

        Optional<BorrowingHistory> borrowingHistoryOptional = borrowingHistoryRepository
                .findById(borrowingHistoryId);
        if (borrowingHistoryOptional.isEmpty()) {
            log.error("Error while retrieving borrowingHistory with id = {}, Reason: Not Found", borrowingHistoryId);
            throw new EntityNotFoundException(EntityName.BorrowingHistory, borrowingHistoryId);
        }

        var borrowingHistory = borrowingHistoryOptional.get();
        borrowingHistory.setReturnedDate(LocalDateTime.now());
        borrowingHistory.getBorrowedBook().setBorrowed(false);
        log.info("Updated borrowingHistory with id = {}", borrowingHistoryId);

        return borrowingHistoryMapper.mapBorrowingHistoryToBorrowingHistoryResponseDto(borrowingHistory);
    }
}
