package dev.library.management.system.service.impl;

import dev.library.management.system.domain.dto.request.AuthorRequestDto;
import dev.library.management.system.domain.dto.response.AuthorResponseDto;
import dev.library.management.system.domain.entity.Author;
import dev.library.management.system.domain.enums.EntityName;
import dev.library.management.system.domain.mapper.AuthorMapper;
import dev.library.management.system.exception.notfound.EntityNotFoundException;
import dev.library.management.system.repository.AuthorRepository;
import dev.library.management.system.service.aop.annotation.Loggable;
import dev.library.management.system.service.interfaces.AuthorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Loggable(className = "RoleServiceImpl")
public class AuthorServiceImpl implements AuthorService {
    /* Repositories */
    private final AuthorRepository authorRepository;

    /* Mappers */
    private final AuthorMapper authorMapper;

    @Override
    public List<AuthorResponseDto> getAllAuthors() {
        return authorRepository.findAll()
                .stream()
                .map(authorMapper::mapAuthorToAuthorResponseDto)
                .toList();
    }

    @Override
    public AuthorResponseDto getAuthorById(final long id) throws EntityNotFoundException {
        Optional<Author> authorOptional = authorRepository.findById(id);

        if (authorOptional.isEmpty()) {
            log.error("Error while retrieving an author with id = {}, Reason: Not Found", id);
            throw new EntityNotFoundException(EntityName.Author, id);
        }

        return authorMapper.mapAuthorToAuthorResponseDto(authorOptional.get());
    }

    @Override
    public List<AuthorResponseDto> getAuthorsByFirstNameAndLastName(
            final String firstName,
            final String lastName
    ) {
        List<Author> authors = authorRepository.findByFirstNameAndLastName(firstName, lastName);

        return authors.stream().map(authorMapper::mapAuthorToAuthorResponseDto).toList();
    }

    @Override
    public AuthorResponseDto saveAuthor(final AuthorRequestDto authorRequestDto) {
        Author author = authorMapper.mapAuthorRequestDtoToAuthor(authorRequestDto);
        authorRepository.save(author);

        return authorMapper.mapAuthorToAuthorResponseDto(author);
    }

    @Override
    public AuthorResponseDto updateAuthor(
            final long id,
            final String biography
    ) throws EntityNotFoundException {
        Optional<Author> authorOptional = authorRepository.findById(id);

        if (authorOptional.isEmpty()) {
            log.error("Error while updating an author with id = {}, Reason: Not Found", id);
            throw new EntityNotFoundException(EntityName.Author, id);
        }

        Author author = authorOptional.get();
        author.setBiography(biography);

        return authorMapper.mapAuthorToAuthorResponseDto(authorRepository.save(author));
    }

    @Override
    @Transactional
    public AuthorResponseDto deleteAuthor(final long id) throws EntityNotFoundException {
        Optional<Author> authorOptional = authorRepository.findById(id);

        if (authorOptional.isEmpty()) {
            log.error("Error while deleting an author with id = {}, Reason: Not Found", id);
            throw new EntityNotFoundException(EntityName.Author, id);
        }

        authorRepository.deleteById(id);

        Author author = authorOptional.get();
        return authorMapper.mapAuthorToAuthorResponseDto(author);
    }
}
