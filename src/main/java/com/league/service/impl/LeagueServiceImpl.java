package com.league.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.league.entity.League;
import com.league.repo.LeagueRepo;
import com.league.service.LeagueService;

public class LeagueServiceImpl implements LeagueService {

	@Autowired
	private LeagueRepo leagueRepo;
	
	@Override
	public List<League> findAll() {
		return leagueRepo.findAll();
	}

	@Override
	public Optional<League> findById(long id) {
		return leagueRepo.findById(id);
	}

	@Override
	public Optional<League> findByName(String name) {
		return leagueRepo.findByName(name);
	}

	@Override
	public List<League> findByAvailable(boolean available) {
		return leagueRepo.findByAvailable(available);
	}

	@Override
	public boolean setAvaialbleTo(long id, boolean available) {
		Optional<League> opt = leagueRepo.findById(id);
		if (opt.isPresent()) {
			League league = opt.get();
			league.setAvailable(available);
			leagueRepo.save(league);
			return true;
		}
		
		return false;
	}

	@Override
	public boolean saveNew(League league) {
		Optional<League> opt = leagueRepo.findById(league.getId());
		if (opt.isPresent()) {
			return false;
		}

		leagueRepo.save(league);
		return true;
	}

	@Override
	public boolean update(League league) {
		Optional<League> opt = leagueRepo.findById(league.getId());
		if (opt.isPresent()) {
			leagueRepo.save(league);
			return true;
		}
		return false;
	}

}
