package dev.library.management.system.repository;

import dev.library.management.system.domain.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query("""
        SELECT a FROM Author a WHERE a.firstName LIKE %:firstName% AND a.lastName LIKE %:lastName%
    """)
    List<Author> findByFirstNameAndLastName(String firstName, String lastName);

}
