package dev.library.management.system.service.impl;

import dev.library.management.system.domain.dto.request.BookRequestDto;
import dev.library.management.system.domain.dto.response.BookResponseDto;
import dev.library.management.system.domain.entity.Author;
import dev.library.management.system.domain.entity.Book;
import dev.library.management.system.domain.entity.Category;
import dev.library.management.system.domain.enums.EntityName;
import dev.library.management.system.domain.enums.Genre;
import dev.library.management.system.domain.mapper.BookMapper;
import dev.library.management.system.exception.notfound.EntityNotFoundException;
import dev.library.management.system.repository.AuthorRepository;
import dev.library.management.system.repository.BookRepository;
import dev.library.management.system.repository.CategoryRepository;
import dev.library.management.system.service.aop.annotation.Loggable;
import dev.library.management.system.service.aop.annotation.Timed;
import dev.library.management.system.service.interfaces.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Loggable(className = "BookServiceImpl")
@Timed
public class BookServiceImpl implements BookService {

    /* Repositories */
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;

    /* Mappers */
    private final BookMapper bookMapper;


    @Override
    public List<BookResponseDto> getAllBooks() {
        return bookRepository.findAllBooks()
                .stream()
                .map(bookMapper::mapBookToBookResponseDto)
                .toList();
    }

    @Override
    public List<BookResponseDto> getAllBooksWithPagination(
            final int pageNumber,
            final int pageSize
    ) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Book> booksPage = bookRepository.findAllWithPagination(pageable);

        return booksPage.getContent().stream().map(bookMapper::mapBookToBookResponseDto).toList();
    }

    @Override
    public BookResponseDto getBookById(final long id) throws EntityNotFoundException {
        Optional<Book> bookOptional = bookRepository.findById(id);

        if (bookOptional.isEmpty()) {
            log.error("Error while retrieving a book with id = {}, Reason: Not Found", id);
            throw new EntityNotFoundException(EntityName.Book, id);
        }

        return bookMapper.mapBookToBookResponseDto(bookOptional.get());
    }

    @Override
    public List<BookResponseDto> getBooksByTitle(final String title) {
        List<Book> books = bookRepository.findByTitle(title);
        return books.stream()
                .map(bookMapper::mapBookToBookResponseDto)
                .toList();
    }

    @Override
    public List<BookResponseDto> getBooksByGenre(final Genre genre) {
        List<Book> books = bookRepository.findAllByGenre(genre);

        return books.stream().map(bookMapper::mapBookToBookResponseDto).toList();
    }

    @Override
    public List<BookResponseDto> searchFilteredBooksPage(
            final String title,
            final Long authorId,
            final Genre genre,
            final int pageNumber,
            final int pageSize
    ) {
        if (!Objects.isNull(authorId)) {
            Optional<Author> authorOptional = authorRepository.findById(authorId);
            if (authorOptional.isEmpty()) {
                log.error("Error while retrieving a book with id = {}, Reason: Not Found", authorId);
                throw new EntityNotFoundException(EntityName.Author, authorId);
            }
        }

        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Book> filteredBooks = bookRepository.findAllBySpecifiedParameters(
                title, authorId, genre, pageable
        );

        return filteredBooks.getContent().stream().map(bookMapper::mapBookToBookResponseDto).toList();
    }

    @Override
    public List<BookResponseDto> getBooksByAuthorId(final long authorId) {
        Optional<Author> authorOptional = authorRepository.findById(authorId);
        if (authorOptional.isEmpty()) {
            log.error("Error while retrieving a book with id = {}, Reason: Not Found", authorId);
            throw new EntityNotFoundException(EntityName.Author, authorId);
        }

        List<Book> books = bookRepository.findAllByAuthorId(authorId);

        return books
                .stream()
                .map(bookMapper::mapBookToBookResponseDto)
                .toList();
    }

    @Override
    public List<BookResponseDto> getBooksByAuthorFirstName(final String authorFirstName) {
        List<Book> books = bookRepository.findAllByAuthorFirstName(authorFirstName);

        return books
                .stream()
                .map(bookMapper::mapBookToBookResponseDto)
                .toList();
    }

    @Override
    public BookResponseDto saveBook(final BookRequestDto bookRequestDto) throws EntityNotFoundException {
        Book book = bookMapper.mapBookRequestDtoToBook(bookRequestDto);
        book.setBorrowed(false);

        Optional<Author> authorOptional = authorRepository.findById(bookRequestDto.authorId());

        if (authorOptional.isEmpty()) {
            log.error("Error while retrieving author with id = {}, Reason: Not Found", bookRequestDto.authorId());
            throw new EntityNotFoundException(EntityName.Author, bookRequestDto.authorId());
        }

        book.setAuthor(authorOptional.get());

        List<Category> categories = categoryRepository.findAllByIdIn(bookRequestDto.categoryIds());
        book.setCategories(categories);

        bookRepository.save(book);
        return bookMapper.mapBookToBookResponseDto(book);
    }

    @Override
    @Transactional
    public BookResponseDto deleteBookById(final long id) throws EntityNotFoundException {
        Optional<Book> bookOptional = bookRepository.findById(id);

        if (bookOptional.isEmpty()) {
            log.error("Error while deleting a book with id = {}, Reason: Not Found", id);
            throw new EntityNotFoundException(EntityName.Book, id);
        }

        bookRepository.deleteById(id);

        Book book = bookOptional.get();
        return bookMapper.mapBookToBookResponseDto(book);
    }
}
