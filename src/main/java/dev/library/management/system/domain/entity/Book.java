package dev.library.management.system.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "BOOKS")
@Getter
@Setter
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String title;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(
            name = "AUTHOR_ID",
            foreignKey = @ForeignKey(name = "FK_BOOKS_AUTHORS_AUTHOR_ID")
    )
    private Author author;

    @ManyToMany
    @JoinTable(
            name = "BOOKS_CATEGORIES",
            joinColumns = @JoinColumn(
                    name = "BOOK_ID",
                    foreignKey = @ForeignKey(name = "FK_BOOKS_CATEGORIES_BOOK_ID")
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "CATEGORY_ID",
                    foreignKey = @ForeignKey(name = "FK_BOOKS_CATEGORIES_CATEGORY_ID")
            )
    )
    private List<Category> categories;

    @OneToMany(mappedBy = "borrowedBooks")
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
                ", author=" + author +
                '}';
    }
}
