package com.league.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.league.entity.Club;
import com.league.entity.League;

@Repository
public interface ClubRepo extends JpaRepository<Club, Long> {

	Optional<League> findByName(Long id);
}
