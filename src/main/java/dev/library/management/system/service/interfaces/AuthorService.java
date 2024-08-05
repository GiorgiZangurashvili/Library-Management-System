package dev.library.management.system.service.interfaces;

import dev.library.management.system.domain.dto.request.AuthorRequestDto;
import dev.library.management.system.domain.dto.response.AuthorResponseDto;
import dev.library.management.system.exception.notfound.EntityNotFoundException;

import java.util.List;

public interface AuthorService {

    List<AuthorResponseDto> getAllAuthors();
    AuthorResponseDto getAuthorById(long id) throws EntityNotFoundException;
    List<AuthorResponseDto> getAuthorsByFirstNameAndLastName(String firstName, String lastName);
    AuthorResponseDto saveAuthor(AuthorRequestDto authorRequestDto);
    AuthorResponseDto updateAuthor(long id, String biography) throws EntityNotFoundException;
    AuthorResponseDto deleteAuthor(long id) throws EntityNotFoundException;

}
