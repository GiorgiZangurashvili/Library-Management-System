package dev.library.management.system.repository;

import dev.library.management.system.domain.entity.Book;
import dev.library.management.system.domain.enums.Genre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("""
        SELECT b FROM Book b
        JOIN b.categories c
        WHERE (:title IS NULL OR b.title LIKE %:title%)
            AND (:authorId IS NULL OR b.author.id = :authorId)
            AND (:genre IS NULL OR c.genre = :genre)
    """)
    Page<Book> findAllBySpecifiedParameters(
            String title,
            Long authorId,
            Genre genre,
            Pageable pageable
    );

    @Query("""
        SELECT b FROM Book b WHERE b.title = :title
    """)
    List<Book> findByTitle(String title);

    @Query("""
        SELECT b FROM Book b WHERE b.author.id = :authorId
    """)
    List<Book> findAllByAuthorId(long authorId);

    @Query("""
        SELECT b FROM Book b WHERE b.author.firstName LIKE %:authorFirstName%
    """)
    List<Book> findAllByAuthorFirstName(String authorFirstName);

    @Query("""
        SELECT b FROM Book b
        JOIN b.categories c
        WHERE c.genre = :genre
    """)
    List<Book> findAllByGenre(Genre genre);

}
