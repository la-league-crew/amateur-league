package com.league.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.league.entity.ClubRepresentation;
import com.league.entity.League;
import com.league.repo.ClubRepresentationRepo;
import com.league.service.ClubRepresentationService;

@Service
public class ClubRepresentationServiceImpl implements ClubRepresentationService {

	@Autowired
	private ClubRepresentationRepo crRepo;
	
	@Override
	public Optional<ClubRepresentation> findOne(Long id) {
		return crRepo.findById(id);
	}

	@Override
	public List<ClubRepresentation> findAll() {
		return crRepo.findAll();
	}

	@Override
	public ClubRepresentation save(ClubRepresentation dto) {
		return crRepo.save(dto);
	}

	@Override
	public ClubRepresentation update(Long id, ClubRepresentation dto) {
		Optional<ClubRepresentation> optional = crRepo.findById(id);
		if (!optional.isPresent()) {
			throw new IllegalArgumentException();
		}
		ClubRepresentation cr = optional.get();
		//TODO
		return cr;
	}

	@Override
	public void delete(Long id) {
		Optional<ClubRepresentation> optional = crRepo.findById(id);
		if (!optional.isPresent()) {
			throw new IllegalArgumentException();
		}
		crRepo.deleteById(id);
	}

	@Override
	public void setAvaialbleTo(long id, boolean available) {
		Optional<ClubRepresentation> optional = crRepo.findById(id);
		if (!optional.isPresent()) {
			throw new IllegalArgumentException();
		}
		ClubRepresentation cr = optional.get();
		//TODO
	}

	
}
