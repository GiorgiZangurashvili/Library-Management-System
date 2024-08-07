package dev.library.management.system.service.interfaces;

import dev.library.management.system.domain.dto.request.AuthorRequestDto;
import dev.library.management.system.domain.dto.response.AuthorResponseDto;
import dev.library.management.system.exception.notfound.EntityNotFoundException;

import java.util.List;

/**
 * Interface for Author Service operations.
 * This interface defines methods for managing authors including
 * retrieving, saving, and deleting authors, as well as searching for authors
 * by various criteria.
 */
public interface AuthorService {

    /**
     * Retrieves all authors from the database.
     *
     * @return A list of AuthorResponseDto objects representing all authors.
     */
    List<AuthorResponseDto> getAllAuthors();

    /**
     * Retrieves an author by their ID.
     *
     * @param id The ID of the author to retrieve.
     * @return An AuthorResponseDto object representing the found author.
     * @throws EntityNotFoundException If no author is found with the given ID.
     */
    AuthorResponseDto getAuthorById(long id) throws EntityNotFoundException;

    /**
     * Retrieves authors by their first name and last name.
     *
     * @param firstName The first name of the author(s) to search for.
     * @param lastName The last name of the author(s) to search for.
     * @return A list of AuthorResponseDto objects representing the found authors.
     */
    List<AuthorResponseDto> getAuthorsByFirstNameAndLastName(String firstName, String lastName);

    /**
     * Saves a new author to the database.
     *
     * @param authorRequestDto The AuthorRequestDto object containing the author details to save.
     * @return An AuthorResponseDto object representing the saved author.
     */
    AuthorResponseDto saveAuthor(AuthorRequestDto authorRequestDto);

    /**
     * Updates an existing author's biography.
     *
     * @param id The ID of the author to update.
     * @param biography The new biography for the author.
     * @return An AuthorResponseDto object representing the updated author.
     * @throws EntityNotFoundException If no author is found with the given ID.
     */
    AuthorResponseDto updateAuthor(long id, String biography) throws EntityNotFoundException;

    /**
     * Deletes an author from the database.
     *
     * @param id The ID of the author to delete.
     * @return An AuthorResponseDto object representing the deleted author.
     * @throws EntityNotFoundException If no author is found with the given ID.
     */
    AuthorResponseDto deleteAuthor(long id) throws EntityNotFoundException;

}
