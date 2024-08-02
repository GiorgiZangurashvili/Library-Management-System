package dev.library.management.system.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(
        name = "BORROWING_HISTORY",
        indexes = {
                @Index(name = "IDX_BORROWING_HISTORY_USER_ID", columnList = "USER_ID"),
                @Index(name = "IDX_BORROWING_HISTORY_BOOK_ID", columnList = "BOOK_ID")
        }
)
@Getter
@Setter
public class BorrowingHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private LocalDateTime borrowedDate;

    private LocalDateTime returnedDate;

    @ManyToOne
    @JoinColumn(
            name = "BORROWER_ID",
            foreignKey = @ForeignKey(name = "FK_BORROWING_HISTORY_USER_ID"),
            nullable = false
    )
    private User borrowingUser;

    @ManyToOne
    @JoinColumn(
            name = "BORROWED_BOOK_ID",
            foreignKey = @ForeignKey(name = "FK_BORROWING_HISTORY_BOOK_ID"),
            nullable = false
    )
    private Book borrowedBook;

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
                ", returnedDate=" + returnedDate +
                ", borrowingUser=" + borrowingUser +
                ", borrowedBook=" + borrowedBook +
                '}';
    }
}
