package dev.library.management.system.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Entity
@Table(
        name = "BOOKS",
        indexes = {
                @Index(name = "IDX_BOOK_TITLE", columnList = "TITLE"),
                @Index(name = "IDX_BOOK_AUTHOR_ID", columnList = "AUTHOR_ID")
        }
)
@Getter
@Setter
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String title;

    private boolean isBorrowed;

    @ManyToOne
    @JoinColumn(
            name = "AUTHOR_ID",
            foreignKey = @ForeignKey(name = "FK_BOOKS_AUTHORS_AUTHOR_ID")
    )
    private Author author;

    @ManyToMany(cascade = CascadeType.REMOVE)
    @JoinTable(
            name = "BOOKS_CATEGORIES",
            joinColumns = @JoinColumn(
                    name = "BOOK_ID",
                    foreignKey = @ForeignKey(name = "FK_BOOKS_CATEGORIES_BOOK_ID")
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "CATEGORY_ID",
                    foreignKey = @ForeignKey(name = "FK_BOOKS_CATEGORIES_CATEGORY_ID")
            ),
            indexes = {
                    @Index(name = "IDX_BOOK_ID", columnList = "BOOK_ID"),
                    @Index(name = "IDX_CATEGORY_ID", columnList = "CATEGORY_ID")
            }
    )
    private List<Category> categories;

    @OneToMany(mappedBy = "borrowedBook")
    private List<BorrowingHistory> borrowingHistory;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", isBorrowed=" + isBorrowed +
                ", author=" + author +
                '}';
    }
}
