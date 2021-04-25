package com.league.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.league.entity.Season;

@Repository
public interface SeasonRepo extends JpaRepository<Season, Long>{

}
