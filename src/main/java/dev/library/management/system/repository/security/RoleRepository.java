package dev.library.management.system.repository.security;

import dev.library.management.system.domain.entity.security.Role;
import dev.library.management.system.domain.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query("""
        SELECT r FROM Role r
        WHERE r.name = :roleName
    """)
    Optional<Role> findRoleByRoleName(RoleName roleName);

}
