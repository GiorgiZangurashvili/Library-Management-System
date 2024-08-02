package dev.library.management.system.service;

import dev.library.management.system.domain.dto.response.BookResponseDto;
import dev.library.management.system.domain.entity.Book;
import dev.library.management.system.domain.mapper.AuthorMapper;
import dev.library.management.system.domain.mapper.BookMapper;
import dev.library.management.system.domain.mapper.BorrowingHistoryMapper;
import dev.library.management.system.repository.AuthorRepository;
import dev.library.management.system.repository.BookRepository;
import dev.library.management.system.repository.CategoryRepository;
import dev.library.management.system.service.impl.BookServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private AuthorRepository authorRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @Spy
    private AuthorMapper authorMapper;

    @Spy
    private BorrowingHistoryMapper borrowingHistoryMapper;

    @Spy
    private BookMapper bookMapper;

    @InjectMocks
    private BookServiceImpl bookService;

    @Test
    public void test_getAll() {
        Book book1 = new Book(1L, "Title_1", false, null, null, null);
        Book book2 = new Book(2L, "Title_2", false, null, null, null);

        given(bookRepository.findAll())
                .willReturn(List.of(book1, book2));

        List<BookResponseDto> allBooks = bookService.getAllBooks();

        assertThat(allBooks).isNotNull();
        assertThat(allBooks.size()).isEqualTo(2);
    }

    @Test
    public void test_getAllBooksWithPagination() {
        int pageNumber = 0;
        int pageSize = 2;
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        Book book1 = Book
                .builder()
                .id(1L)
                .title("Title_1")
                .build();
        Book book2 = Book
                .builder()
                .id(2L)
                .title("Title_2")
                .build();
        Book book3 = Book
                .builder()
                .id(3L)
                .title("Title_3")
                .build();

        List<Book> books = List.of(book1, book2, book3);

        Page<Book> bookPage = new PageImpl<>(books);

        given(bookRepository.findAll(pageable))
                .willReturn(bookPage);

        List<BookResponseDto> bookResponseDtos = bookService
                .getAllBooksWithPagination(pageNumber, pageSize);

        System.out.println(bookResponseDtos);
        assertThat(bookResponseDtos).isNotNull();
        assertThat(bookResponseDtos.size()).isEqualTo(2);
    }

}
