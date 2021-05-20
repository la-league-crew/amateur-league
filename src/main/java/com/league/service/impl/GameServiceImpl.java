package com.league.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.league.entity.ClubRepresentation;
import com.league.entity.Game;
import com.league.repo.GameRepo;
import com.league.service.GameService;

public class GameServiceImpl implements GameService{

	@Autowired
	private GameRepo gameRepo;
	
	@Override
	public Optional<Game> findOne(Long id) {
		return gameRepo.findById(id);
	}

	@Override
	public List<Game> findAll() {
		return gameRepo.findAll();
	}

	@Override
	public Game save(Game dto) {
		return gameRepo.save(dto);
	}

	@Override
	public Game update(Long id, Game dto) {
		Optional<Game> optional = gameRepo.findById(id);
		if (!optional.isPresent()) {
			throw new IllegalArgumentException();
		}
		Game game = optional.get();
		//TODO
		return game;
	}

	@Override
	public void delete(Long id) {
		Optional<Game> optional = gameRepo.findById(id);
		if (!optional.isPresent()) {
			throw new IllegalArgumentException();
		}
		gameRepo.deleteById(id);
	}

	@Override
	public void setAvaialbleTo(long id, boolean available) {
		Optional<Game> optional = gameRepo.findById(id);
		if (!optional.isPresent()) {
			throw new IllegalArgumentException();
		}
		Game game = optional.get();
		//TODO set available
	}

}
