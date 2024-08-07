package dev.library.management.system.controller;

import dev.library.management.system.domain.dto.request.BookRequestDto;
import dev.library.management.system.domain.dto.response.BookResponseDto;
import dev.library.management.system.domain.enums.Genre;
import dev.library.management.system.service.interfaces.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/books")
@RequiredArgsConstructor
@Tag(name = "Books API")
public class BookController {
    private final BookService bookService;

    @GetMapping
    @Operation(
            description = "View a list of available books",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved list of books"
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "You are not authorized to view the resource"
                    )
            }
    )
    public ResponseEntity<List<BookResponseDto>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @GetMapping("/page")
    @Operation(
            description = "View a page of books",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved page of books"
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "You are not authorized to view the resource"
                    )
            }
    )
    public ResponseEntity<List<BookResponseDto>> getAllBooksPage(
            @RequestParam(required = false, defaultValue = "0") int pageNumber,
            @RequestParam(required = false, defaultValue = "20") int pageSize
    ) {
        return ResponseEntity.ok(bookService.getAllBooksWithPagination(pageNumber, pageSize));
    }

    @GetMapping("/book")
    @Operation(
            description = "Get book by id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved book"
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "You are not authorized to view the resource"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "The book with specified id is not found"
                    )
            }
    )
    public ResponseEntity<BookResponseDto> getBookById(@RequestParam long id) {
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    @GetMapping("/title")
    @Operation(
            description = "View a list of books by title",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved list of books by title"
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "You are not authorized to view the resource"
                    )
            }
    )
    public ResponseEntity<List<BookResponseDto>> getBooksByTitle(@RequestParam String title) {
        return ResponseEntity.ok(bookService.getBooksByTitle(title));
    }

    @GetMapping("/genre")
    @Operation(
            description = "View a list of books by genre",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved list of books by genre"
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "You are not authorized to view the resource"
                    )
            }
    )
    public ResponseEntity<List<BookResponseDto>> getBooksByGenre(@RequestParam Genre genre) {
        return ResponseEntity.ok(bookService.getBooksByGenre(genre));
    }

    @GetMapping("/filter")
    @Operation(
            description = "View a page of books by applying filter",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved a list of books by applying filter"
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "You are not authorized to view the resource"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "The author with specified authorId is not found"
                    )
            }
    )
    public ResponseEntity<List<BookResponseDto>> getBooksPageByFilter(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Long authorId,
            @RequestParam(required = false) Genre genre,
            @RequestParam(required = false, defaultValue = "0") int pageNumber,
            @RequestParam(required = false, defaultValue = "20") int pageSize
    ) {
        return ResponseEntity.ok(bookService.searchFilteredBooksPage(title, authorId, genre, pageNumber, pageSize));
    }

    @GetMapping("/author/id")
    @Operation(
            description = "View a list of books by authorId",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved a list of books by authorId"
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "You are not authorized"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "The author with specified authorId is not found"
                    )
            }
    )
    public ResponseEntity<List<BookResponseDto>> getBooksByAuthorId(@RequestParam long authorId) {
        return ResponseEntity.ok(bookService.getBooksByAuthorId(authorId));
    }

    @GetMapping("/author/firstName")
    @Operation(
            description = "View a list of books by author's firstName",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved a list of books by authorFirstName"
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "You are not authorized"
                    )
            }
    )
    public ResponseEntity<List<BookResponseDto>> getBooksByAuthorFirstName(@RequestParam String authorFirstName) {
        return ResponseEntity.ok(bookService.getBooksByAuthorFirstName(authorFirstName));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'LIBRARIAN')")
    @Operation(
            description = "Save a new book",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully saved a new book in library"
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
                            description = "The author with specified authorId is not found"
                    )
            }
    )
    public ResponseEntity<BookResponseDto> saveBook(@RequestBody @Valid BookRequestDto bookRequestDto) {
        return ResponseEntity.ok(bookService.saveBook(bookRequestDto));
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasAnyRole('ADMIN', 'LIBRARIAN')")
    @Operation(
            description = "Delete a book",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully deleted a book from library"
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
                            description = "Book by specified id is not found"
                    )
            }
    )
    public ResponseEntity<BookResponseDto> deleteBookById(@RequestParam long id) {
        return ResponseEntity.ok(bookService.deleteBookById(id));
    }

}
