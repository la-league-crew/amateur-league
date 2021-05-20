package com.league.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.league.entity.Round;
import com.league.repo.RoundRepo;
import com.league.service.RoundService;

@Service
public class RoundServiceImpl implements RoundService {

	@Autowired
	private RoundRepo roundRepo;
	
	@Override
	public Optional<Round> findOne(Long id) {
		return roundRepo.findById(id);
	}

	@Override
	public List<Round> findAll() {
		return roundRepo.findAll();
	}

	@Override
	public Round save(Round dto) {
		return roundRepo.save(dto);
	}

	@Override
	public Round update(Long id, Round dto) {
		Optional<Round> optional = roundRepo.findById(id);
		if (!optional.isPresent()) {
			throw new IllegalArgumentException();
		}
		Round round = optional.get();
		//TODO
		return null;
	}

	@Override
	public void delete(Long id) {
		Optional<Round> optional = roundRepo.findById(id);
		if (!optional.isPresent()) {
			throw new IllegalArgumentException();
		}
		roundRepo.deleteById(id);
	}

	@Override
	public void setAvaialbleTo(long id, boolean available) {
		Optional<Round> optional = roundRepo.findById(id);
		if (!optional.isPresent()) {
			throw new IllegalArgumentException();
		}
		Round round = optional.get();
		//TODO	
	}

}
