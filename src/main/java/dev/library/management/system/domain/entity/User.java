package dev.library.management.system.domain.entity;

import dev.library.management.system.domain.entity.security.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Entity
@Table(
        name = "USERS",
        uniqueConstraints = @UniqueConstraint(
                name = "UNIQUE_CONSTRAINT_USER_USERNAME",
                columnNames = "USERNAME"
        )
)
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "borrowingUser")
    private List<BorrowingHistory> borrowingHistory;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "USER_ROLES",
            joinColumns = @JoinColumn(
                    name = "USER_ID",
                    foreignKey = @ForeignKey(name = "FK_USERS_ROLES_USER_ID")
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "ROLE_ID",
                    foreignKey = @ForeignKey(name = "FK_USERS_ROLES_ROLE_ID")
            )
    )
    private List<Role> roles;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
