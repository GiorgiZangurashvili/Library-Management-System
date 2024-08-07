package dev.library.management.system.controller;

import dev.library.management.system.domain.dto.response.BorrowingHistoryResponseDto;
import dev.library.management.system.service.interfaces.BorrowingHistoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/borrowingHistory")
@RequiredArgsConstructor
@Tag(name = "Borrowing History API")
public class BorrowingHistoryController {
    private final BorrowingHistoryService borrowingHistoryService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'LIBRARIAN', 'USER')")
    @Operation(
            description = "View a borrowing history",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved borrowing history"
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "You are not authorized"
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "You have no authority to do this action"
                    )
            }
    )
    public ResponseEntity<List<BorrowingHistoryResponseDto>> getBorrowingHistory() {
        return ResponseEntity.ok(borrowingHistoryService.getBorrowingHistory());
    }

    @GetMapping("/user")
    @PreAuthorize("hasAnyRole('ADMIN', 'LIBRARIAN', 'USER')")
    @Operation(
            description = "View borrowing history of a user",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved borrowing history of a user"
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "You are not authorized"
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "You have no authority to do this action"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "User with specified id is not found"
                    )
            }
    )
    public ResponseEntity<List<BorrowingHistoryResponseDto>> getBorrowingHistoryOfUser(
            @RequestParam long userId
    ) {
        return ResponseEntity.ok(borrowingHistoryService.getBorrowingHistoryOfUser(userId));
    }

    @GetMapping("/book")
    @PreAuthorize("hasAnyRole('ADMIN', 'LIBRARIAN', 'USER')")
    @Operation(
            description = "View borrowing history of a book",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved borrowing history of a book"
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "You are not authorized"
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "You have no authority to do this action"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Book with specified id is not found"
                    )
            }
    )
    public ResponseEntity<List<BorrowingHistoryResponseDto>> getBorrowingHistoryOfBook(
            @RequestParam long bookId
    ) {
        return ResponseEntity.ok(borrowingHistoryService.getBorrowingHistoryOfBook(bookId));
    }

    @PostMapping("/borrowBook")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(
            description = "Borrow a book to the user",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully borrowed a book to the user"
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "You are not authorized"
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "You have no authority to do this action"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Book with specified id is not found"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "User with specified id is not found"
                    )
            }
    )
    public ResponseEntity<BorrowingHistoryResponseDto> borrowBook(
            @RequestParam long userId,
            @RequestParam long bookId
    ) {
        return ResponseEntity.ok(borrowingHistoryService.borrowBook(userId, bookId));
    }

    @PutMapping("/returnBook")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(
            description = "Return a book",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully returned a book"
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "You are not authorized"
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "You have no authority to do this action"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "BorrowingHistory with specified id is not found"
                    )
            }
    )
    public ResponseEntity<BorrowingHistoryResponseDto> returnBook(
            @RequestParam long borrowingHistoryId
    ) {
        return ResponseEntity.ok(borrowingHistoryService.returnBook(borrowingHistoryId));
    }
}
