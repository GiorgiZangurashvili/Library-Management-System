package dev.library.management.system.controller;

import dev.library.management.system.domain.dto.response.BorrowingHistoryResponseDto;
import dev.library.management.system.service.interfaces.BorrowingHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/borrowingHistory")
@RequiredArgsConstructor
public class BorrowingHistoryController {
    private final BorrowingHistoryService borrowingHistoryService;

    @GetMapping
    public ResponseEntity<List<BorrowingHistoryResponseDto>> getBorrowingHistory() {
        return ResponseEntity.ok(borrowingHistoryService.getBorrowingHistory());
    }

    @GetMapping("/user")
    public ResponseEntity<List<BorrowingHistoryResponseDto>> getBorrowingHistoryOfUser(
            @RequestParam long userId
    ) {
        return ResponseEntity.ok(borrowingHistoryService.getBorrowingHistoryOfUser(userId));
    }

    @GetMapping("/book")
    public ResponseEntity<List<BorrowingHistoryResponseDto>> getBorrowingHistoryOfBook(
            @RequestParam long bookId
    ) {
        return ResponseEntity.ok(borrowingHistoryService.getBorrowingHistoryOfBook(bookId));
    }

    @PostMapping("/borrowBook")
    public ResponseEntity<BorrowingHistoryResponseDto> borrowBook(
            @RequestParam long userId,
            @RequestParam long bookId
    ) {
        return ResponseEntity.ok(borrowingHistoryService.borrowBook(userId, bookId));
    }

    @PutMapping("/returnBook")
    public ResponseEntity<BorrowingHistoryResponseDto> returnBook(
            @RequestParam long borrowingHistoryId
    ) {
        return ResponseEntity.ok(borrowingHistoryService.returnBook(borrowingHistoryId));
    }
}
