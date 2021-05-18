package com.league.repo;

import com.league.entity.LeagueDevice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeagueDeviceRepo extends JpaRepository<LeagueDevice,Long> {
}
