package com.league.service;

import java.util.List;
import java.util.Optional;

import com.league.entity.League;

public interface LeagueService {

	List<League> findAll();
	
	Optional<League> findById(long id);
	
	Optional<League> findByName(String name);
	
	List<League> findByAvailable(boolean available);
	
	boolean setAvaialbleTo(long id, boolean available);
	
	boolean saveNew(League league);
	
	boolean update(League league);
}
