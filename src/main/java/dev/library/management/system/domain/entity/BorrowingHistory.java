package dev.library.management.system.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "BORROWING_HISTORY")
@Getter
@Setter
public class BorrowingHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private LocalDateTime borrowedDate;

    @ManyToOne
    @JoinColumn(
            name = "BORROWER_ID",
            foreignKey = @ForeignKey(name = "FK_BORROWING_HISTORY_USER_ID"),
            nullable = false
    )
    private User borrowingUsers;

    @ManyToOne
    @JoinColumn(
            name = "BORROWED_BOOK_ID",
            foreignKey = @ForeignKey(name = "FK_BORROWING_HISTORY_BOOK_ID"),
            nullable = false
    )
    private Book borrowedBooks;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BorrowingHistory that = (BorrowingHistory) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "BorrowingHistory{" +
                "id=" + id +
                ", borrowedDate=" + borrowedDate +
                ", borrowingUsers=" + borrowingUsers +
                ", borrowedBooks=" + borrowedBooks +
                '}';
    }
}
