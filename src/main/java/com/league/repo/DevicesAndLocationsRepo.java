package com.league.repo;

import com.league.entity.LeagueUser;
import com.league.entity.DevicesAndLocations;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface DevicesAndLocationsRepo extends JpaRepository<DevicesAndLocations,Long> {
   Optional<DevicesAndLocations> findByLeagueUser(LeagueUser leagueUser);
}
