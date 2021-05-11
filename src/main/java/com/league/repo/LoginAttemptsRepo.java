package com.league.repo;

import com.league.entity.LoginAttempts;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface LoginAttemptsRepo extends JpaRepository<LoginAttempts,Long> {
    Optional<LoginAttempts> findByUsername(String username);
}
