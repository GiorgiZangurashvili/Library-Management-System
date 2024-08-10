package dev.library.management.system.repository;

import dev.library.management.system.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("""
        SELECT u FROM User u
        WHERE u.username = :username
    """)
    Optional<User> findByUsername(String username);

    @Query("""
        SELECT u FROM User u
        WHERE u.isDisabled = :isDisabled
    """)
    List<User> findAllByDisabled(boolean isDisabled);

//    @Query("""
//        DELETE FROM User u
//        WHERE u.id in :userIds
//    """)
//    void deleteAllIn(List<Long> userIds);
    void deleteByIdIn(List<Long> userIds);

}
