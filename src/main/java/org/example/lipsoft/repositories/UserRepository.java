package org.example.lipsoft.repositories;

import org.example.lipsoft.entities.Role;
import org.example.lipsoft.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByName(String name);
    Optional<User> findByEmail(String email);
    Optional<User> findByRole(Role role);
    boolean existsByEmail(String email);
}
