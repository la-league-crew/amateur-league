package com.league.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.league.entity.League;
import com.league.repo.LeagueRepo;
import com.league.service.LeagueService;

@Service
@Transactional
public class LeagueServiceImpl implements LeagueService {

	@Autowired
	private LeagueRepo leagueRepo;

	@Override
	public Optional<League> findOne(Long id) {
		return leagueRepo.findById(id);
	}

	@Override
	public List<League> findAll() {
		return leagueRepo.findAll();
	}

	@Override
	public League save(League dto) {
		return leagueRepo.save(dto);
	}

	@Override
	public League update(Long id, League dto) {
		Optional<League> optional = leagueRepo.findById(id);
		if (!optional.isPresent()) {
			throw new IllegalArgumentException();
		}
		
		League league = optional.get();
		league.setName(dto.getName());
		league.setDescription(dto.getDescription());
		league.setAvailable(dto.isAvailable());
		league.setSeasons(dto.getSeasons());
		return league;
	}

	@Override
	public void delete(Long id) {
		Optional<League> optional = leagueRepo.findById(id);
		if (!optional.isPresent()) {
			throw new IllegalArgumentException();
		}
		leagueRepo.deleteById(id);
	}

	@Override
	public void setAvaialbleTo(long id, boolean available) {
		Optional<League> optional = leagueRepo.findById(id);
		if (!optional.isPresent()) {
			throw new IllegalArgumentException();
		}
		
		League league = optional.get();
		league.setAvailable(available);
	}
}
