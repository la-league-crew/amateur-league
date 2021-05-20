package com.league.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.league.entity.Club;
import com.league.repo.ClubRepo;
import com.league.service.ClubService;

public class ClubServiceImpl implements ClubService {
	
	@Autowired
	private ClubRepo clubRepo;

	@Override
	public Optional<Club> findOne(Long id) {
		return clubRepo.findById(id);
	}

	@Override
	public List<Club> findAll() {
		return clubRepo.findAll();
	}

	@Override
	public Club save(Club dto) {
		return clubRepo.save(dto);
	}

	@Override
	public Club update(Long id, Club dto) {
		Optional<Club> optional = clubRepo.findById(id);
		if (!optional.isPresent()) {
			throw new IllegalArgumentException();
		}
		Club club = optional.get();
		club.setName(dto.getName());
		club.setDescription(dto.getDescription());
		
		return null;
	}

	@Override
	public void delete(Long id) {
		Optional<Club> optional = clubRepo.findById(id);
		if (!optional.isPresent()) {
			throw new IllegalArgumentException();
		}		
		clubRepo.deleteById(id);
	}

	@Override
	public void setAvaialbleTo(long id, boolean available) {
		Optional<Club> optional = clubRepo.findById(id);
		if (!optional.isPresent()) {
			throw new IllegalArgumentException();
		}
		Club club = optional.get();
		club.setAvailable(available);
	}

}
