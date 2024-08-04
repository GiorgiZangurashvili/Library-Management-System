package dev.library.management.system.repository;

import dev.library.management.system.domain.entity.BorrowingHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowingHistoryRepository extends JpaRepository<BorrowingHistory, Long> {

    @Query("""
        SELECT bh FROM BorrowingHistory bh
        JOIN FETCH bh.borrowingUser
        JOIN FETCH bh.borrowedBook
        JOIN FETCH bh.borrowedBook.author
        ORDER BY bh.id
    """)
    List<BorrowingHistory> findAllHistory();

    @Query("""
        SELECT bh FROM BorrowingHistory bh
        JOIN FETCH bh.borrowingUser
        JOIN FETCH bh.borrowedBook
        JOIN FETCH bh.borrowedBook.author
        WHERE bh.borrowingUser.id = :id
        ORDER BY bh.id
    """)
    List<BorrowingHistory> findByBorrowerId(long id);

    @Query("""
        SELECT bh FROM BorrowingHistory bh
        JOIN FETCH bh.borrowingUser
        JOIN FETCH bh.borrowedBook
        JOIN FETCH bh.borrowedBook.author
        WHERE bh.borrowedBook.id = :id
        ORDER BY bh.id
    """)
    List<BorrowingHistory> findByBorrowedBookId(long id);


}
