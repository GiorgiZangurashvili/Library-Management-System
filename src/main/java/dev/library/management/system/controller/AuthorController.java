package dev.library.management.system.controller;

import dev.library.management.system.domain.dto.request.AuthorRequestDto;
import dev.library.management.system.domain.dto.response.AuthorResponseDto;
import dev.library.management.system.service.aop.annotation.Loggable;
import dev.library.management.system.service.interfaces.AuthorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/authors")
@RequiredArgsConstructor
@Tag(name = "Authors API")
@Loggable(className = "AuthorController")
public class AuthorController {
    private final AuthorService authorService;

    @GetMapping
    @Operation(
            description = "View a list of available authors",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved a list of available authors"
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "You are not authorized"
                    )
            }
    )
    public ResponseEntity<List<AuthorResponseDto>> getAllAuthors() {
        return ResponseEntity.ok(authorService.getAllAuthors());
    }

    @GetMapping("/id")
    @Operation(
            description = "Get author by id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved a list of available authors"
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "You are not authorized"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "The author with specified id is not found"
                    )
            }
    )
    public ResponseEntity<AuthorResponseDto> getAuthorById(@RequestParam long id) {
        return ResponseEntity.ok(authorService.getAuthorById(id));
    }

    @GetMapping("/initials")
    @Operation(
            description = "View a list of available authors by first and lastname",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved a list of authors"
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "You are not authorized"
                    )
            }
    )
    public ResponseEntity<List<AuthorResponseDto>> getAuthorsByFirstNameAndLastName(
            @RequestParam String firstName,
            @RequestParam String lastName
    ) {
        return ResponseEntity.ok(authorService.getAuthorsByFirstNameAndLastName(firstName, lastName));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'LIBRARIAN')")
    @Operation(
            description = "Save a new author",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Successfully created a new author"
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
    public ResponseEntity<AuthorResponseDto> saveAuthor(@RequestBody AuthorRequestDto authorRequestDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(authorService.saveAuthor(authorRequestDto));
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'LIBRARIAN')")
    @Operation(
            description = "Update an author by specified id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully updated an author"
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
                            description = "Author with specified id is not found"
                    )
            }
    )
    public ResponseEntity<AuthorResponseDto> updateAuthorBiography(
            @RequestParam long id,
            @RequestBody String biography
    ) {
        return ResponseEntity.ok(authorService.updateAuthor(id, biography));
    }

    @DeleteMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'LIBRARIAN')")
    @Operation(
            description = "Delete an author by id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully deleted an author"
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
                            description = "Author with specified id is not found"
                    )
            }
    )
    public ResponseEntity<AuthorResponseDto> deleteAuthor(@RequestParam long id) {
        return ResponseEntity.ok(authorService.deleteAuthor(id));
    }

}
