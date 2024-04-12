package com.origami.busbuzz.repository;

import com.origami.busbuzz.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);

    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);
}
