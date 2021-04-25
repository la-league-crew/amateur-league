package com.league.repo;

import com.league.entity.LeagueUser;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeagueUserRepo extends JpaRepository<LeagueUser,Long> {

  Optional<LeagueUser> findByEmail(String email);

}
