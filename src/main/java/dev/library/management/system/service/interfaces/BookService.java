package dev.library.management.system.service.interfaces;

import dev.library.management.system.domain.dto.request.BookRequestDto;
import dev.library.management.system.domain.dto.response.BookResponseDto;
import dev.library.management.system.domain.enums.Genre;
import org.springframework.data.domain.Page;

import java.util.List;


public interface BookService {
    List<BookResponseDto> getAllBooks();
    Page<BookResponseDto> getAllBooksWithPagination(int pageNumber, int pageSize);
    BookResponseDto getBookById(long id);
    List<BookResponseDto> getBooksByTitle(String title);
    List<BookResponseDto> getBooksByGenre(Genre genre);
    Page<BookResponseDto> searchFilteredBooksPage(String title, Long authorId, Genre genre, int pageNumber, int pageSize);
    List<BookResponseDto> getBooksByAuthorId(long authorId);
    List<BookResponseDto> getBooksByAuthorFirstName(String authorFirstName);
    BookResponseDto saveBook(BookRequestDto bookRequestDto);
    BookResponseDto deleteBookById(long id);
}
