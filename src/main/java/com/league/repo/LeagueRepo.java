package com.league.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.league.entity.League;

@Repository
public interface LeagueRepo extends JpaRepository<League, Long> {

	Optional<League> findByName(String name);
	
	List<League> findByAvailable(boolean available);
}
