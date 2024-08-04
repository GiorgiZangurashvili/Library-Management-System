package dev.library.management.system.controller;

import dev.library.management.system.domain.dto.request.AuthorRequestDto;
import dev.library.management.system.domain.dto.response.AuthorResponseDto;
import dev.library.management.system.service.interfaces.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/authors")
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService authorService;

    @GetMapping
    public ResponseEntity<List<AuthorResponseDto>> getAllAuthors() {
        return ResponseEntity.ok(authorService.getAllAuthors());
    }

    @GetMapping("/id")
    public ResponseEntity<AuthorResponseDto> getAuthorById(@RequestParam long id) {
        return ResponseEntity.ok(authorService.getAuthorById(id));
    }

    @GetMapping("/initials")
    public ResponseEntity<List<AuthorResponseDto>> getAuthorsByFirstNameAndLastName(
            @RequestParam String firstName,
            @RequestParam String lastName
    ) {
        return ResponseEntity.ok(authorService.getAuthorsByFirstNameAndLastName(firstName, lastName));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'LIBRARIAN')")
    public ResponseEntity<AuthorResponseDto> saveAuthor(@RequestBody AuthorRequestDto authorRequestDto) {
        return ResponseEntity.ok(authorService.saveAuthor(authorRequestDto));
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'LIBRARIAN')")
    public ResponseEntity<AuthorResponseDto> updateAuthorBiography(
            @RequestParam long id,
            @RequestBody String biography
    ) {
        return ResponseEntity.ok(authorService.updateAuthor(id, biography));
    }

    @DeleteMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'LIBRARIAN')")
    public ResponseEntity<AuthorResponseDto> deleteAuthor(@RequestParam long id) {
        return ResponseEntity.ok(authorService.deleteAuthor(id));
    }

}
