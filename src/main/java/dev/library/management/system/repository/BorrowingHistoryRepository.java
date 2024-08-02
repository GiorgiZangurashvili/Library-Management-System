package dev.library.management.system.repository;

import dev.library.management.system.domain.entity.BorrowingHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

// TODO fix n + 1 bugs
@Repository
public interface BorrowingHistoryRepository extends JpaRepository<BorrowingHistory, Long> {

    @Query("""
        SELECT bh FROM BorrowingHistory bh WHERE bh.borrowingUser.id = :id
    """)
    List<BorrowingHistory> findByBorrowerId(long id);

    @Query("""
        SELECT bh FROM BorrowingHistory bh WHERE bh.borrowedBook.id = :id
    """)
    List<BorrowingHistory> findByBorrowedBookId(long id);


}
