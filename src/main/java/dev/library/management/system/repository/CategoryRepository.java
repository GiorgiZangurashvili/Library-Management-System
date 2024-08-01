package dev.library.management.system.repository;

import dev.library.management.system.domain.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("""
        SELECT c FROM Category c WHERE c.id in :ids
    """)
    List<Category> findAllByIdIn(List<Long> ids);

}
