package dev.library.management.system.service.interfaces;

import dev.library.management.system.domain.dto.request.BookRequestDto;
import dev.library.management.system.domain.dto.response.BookResponseDto;
import dev.library.management.system.domain.enums.Genre;
import dev.library.management.system.exception.notfound.EntityNotFoundException;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Interface for Book Service operations.
 * This interface defines methods for managing books including
 * retrieving, saving, and deleting books, as well as searching for books
 * by various criteria.
 */
public interface BookService {
    /**
     * Retrieves a list of all books.
     *
     * @return a list of BookResponseDto objects representing all books.
     */
    List<BookResponseDto> getAllBooks();

    /**
     * Retrieves a paginated list of books.
     *
     * @param pageNumber the page number to retrieve.
     * @param pageSize the number of books per page.
     * @return a list of BookResponseDto objects representing the paginated books.
     */
    List<BookResponseDto> getAllBooksWithPagination(int pageNumber, int pageSize);

    /**
     * Retrieves a book by its ID.
     *
     * @param id the ID of the book to retrieve.
     * @return a BookResponseDto object representing the book.
     * @throws EntityNotFoundException if no book is found with the given ID.
     */
    BookResponseDto getBookById(long id) throws EntityNotFoundException;

    /**
     * Retrieves a list of books by their title.
     *
     * @param title the title of the books to retrieve.
     * @return a list of BookResponseDto objects representing the books with the given title.
     */
    List<BookResponseDto> getBooksByTitle(String title);

    /**
     * Retrieves a list of books by their genre.
     *
     * @param genre the genre of the books to retrieve.
     * @return a list of BookResponseDto objects representing the books with the given genre.
     */
    List<BookResponseDto> getBooksByGenre(Genre genre);

    /**
     * Searches for books with specified parameters and returns a paginated list.
     *
     * @param title the title of the books to search for.
     * @param authorId the ID of the author of the books to search for.
     * @param genre the genre of the books to search for.
     * @param pageNumber the page number to retrieve.
     * @param pageSize the number of books per page.
     * @return a list of BookResponseDto objects representing the filtered and paginated books.
     */
    List<BookResponseDto> searchFilteredBooksPage(String title, Long authorId, Genre genre, int pageNumber, int pageSize);

    /**
     * Retrieves a list of books by the author's ID.
     *
     * @param authorId the ID of the author whose books to retrieve.
     * @return a list of BookResponseDto objects representing the books by the author with the given ID.
     */
    List<BookResponseDto> getBooksByAuthorId(long authorId);

    /**
     * Retrieves a list of books by the author's first name.
     *
     * @param authorFirstName the first name of the author whose books to retrieve.
     * @return a list of BookResponseDto objects representing the books by the author with the given first name.
     */
    List<BookResponseDto> getBooksByAuthorFirstName(String authorFirstName);

    /**
     * Saves a new book.
     *
     * @param bookRequestDto the DTO containing the details of the book to save.
     * @return a BookResponseDto object representing the saved book.
     * @throws EntityNotFoundException if the author or categories specified in the bookRequestDto are not found.
     */
    BookResponseDto saveBook(BookRequestDto bookRequestDto) throws EntityNotFoundException;

    /**
     * Deletes a book by its ID.
     *
     * @param id the ID of the book to delete.
     * @return a BookResponseDto object representing the deleted book.
     * @throws EntityNotFoundException if no book is found with the given ID.
     */
    BookResponseDto deleteBookById(long id) throws EntityNotFoundException;
}
