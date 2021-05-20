package com.league.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.league.entity.Game;

@Repository
public interface GameRepo extends JpaRepository<Game, Long> {

	List<Game> findBySeasonId(Long seasonId);
}
