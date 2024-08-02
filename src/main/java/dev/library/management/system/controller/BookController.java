package dev.library.management.system.controller;

import dev.library.management.system.domain.dto.request.BookRequestDto;
import dev.library.management.system.domain.dto.response.BookResponseDto;
import dev.library.management.system.domain.enums.Genre;
import dev.library.management.system.service.interfaces.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/books")
@RequiredArgsConstructor
public class BookController {
    /* Services */
    private final BookService bookService;

    @GetMapping
    public ResponseEntity<List<BookResponseDto>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @GetMapping("/page")
    public ResponseEntity<List<BookResponseDto>> getAllBooksPage(
            @RequestParam(required = false, defaultValue = "0") int pageNumber,
            @RequestParam(required = false, defaultValue = "20") int pageSize
    ) {
        return ResponseEntity.ok(bookService.getAllBooksWithPagination(pageNumber, pageSize));
    }

    @GetMapping("/book")
    public ResponseEntity<BookResponseDto> getBookById(@RequestParam long id) {
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    @GetMapping("/title")
    public ResponseEntity<List<BookResponseDto>> getBooksByTitle(@RequestParam String title) {
        return ResponseEntity.ok(bookService.getBooksByTitle(title));
    }

    @GetMapping("/genre")
    public ResponseEntity<List<BookResponseDto>> getBooksByGenre(@RequestParam Genre genre) {
        return ResponseEntity.ok(bookService.getBooksByGenre(genre));
    }

    @GetMapping("/filter")
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
    public ResponseEntity<List<BookResponseDto>> getBooksByAuthorId(@RequestParam long authorId) {
        return ResponseEntity.ok(bookService.getBooksByAuthorId(authorId));
    }

    @GetMapping("/author/firstName")
    public ResponseEntity<List<BookResponseDto>> getBooksByAuthorFirstName(@RequestParam String authorFirstName) {
        return ResponseEntity.ok(bookService.getBooksByAuthorFirstName(authorFirstName));
    }

    @PostMapping
    public ResponseEntity<BookResponseDto> saveBook(@RequestBody @Valid BookRequestDto bookRequestDto) {
        return ResponseEntity.ok(bookService.saveBook(bookRequestDto));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<BookResponseDto> deleteBookById(@RequestParam long id) {
        return ResponseEntity.ok(bookService.deleteBookById(id));
    }

}
