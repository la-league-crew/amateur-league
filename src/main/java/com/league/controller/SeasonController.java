package com.league.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.league.entity.Game;
import com.league.entity.Season;
import com.league.repo.SeasonRepo;
import com.league.service.SeasonService;

@RestController
@RequestMapping(path = "/season")
public class SeasonController {

	@Autowired
	private SeasonRepo repo;
	
	@Autowired
	private SeasonService service;
	
	@GetMapping(path = "/findAll")
	public ResponseEntity<List<Season>> getAll() {
		return ResponseEntity.ok(service.findAll());
	}
	
	@GetMapping(path = "/find/{id}")
	public ResponseEntity<Optional<Season>> getById(@PathVariable long id) {
		return ResponseEntity.ok(service.findOne(id));
	}
	
	@PostMapping(path = "/save")
	public ResponseEntity<Season> saveNewSeason(@RequestBody Season season) {
		return ResponseEntity.ok(service.save(season));
	}	
	
	@PutMapping(path = "/update/{id}")
	public ResponseEntity<Season> updateSeason(@PathVariable long id, @RequestBody Season season) {
		return ResponseEntity.ok(service.update(id, season));
	}	
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Season> deleteSeason(@PathVariable long id) {
		service.delete(id);
		return ResponseEntity.ok().build();
	}	
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<?> getRounds(@PathVariable Long id) {
		return ResponseEntity.ok(service.getRounds(id));
	}
	
	@PutMapping(path = "/setAvailable/{id}")
	public ResponseEntity<?> getRounds(@PathVariable long id, @RequestParam boolean available) {
		service.setAvaialbleTo(id, available);
		return ResponseEntity.ok().build();
	}
	
	@PostMapping(path = "/addClub/{seasonId}")
	public ResponseEntity<?> addClubToSeason(@PathVariable long seasonId, @RequestParam long clubId) {
		service.addClubToSeason(seasonId, clubId);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping(path = "/deleteClub/{seasonId}")
	public ResponseEntity<?> deleteClubFromSeason(@PathVariable long seasonId, @RequestParam long clubId) {
		service.deleteClubFromSeason(seasonId, clubId);
		return ResponseEntity.ok().build();
	}
	
	@PostMapping(path = "/makeRounds/{seasonId}")
	public ResponseEntity<?> deleteClubFromSeason(@PathVariable long seasonId) {
		service.makeRounds(seasonId);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping(path = "/rounds/{seasonId}")
	public ResponseEntity<?> getRoundsFromSeason(@PathVariable long seasonId) {
		return ResponseEntity.ok(service.getRounds(seasonId));
	}
	
	@PutMapping(path = "/updateGames/{seasonId}")
	public ResponseEntity<?> updateGamesInSeason(@PathVariable long seasonId, @RequestBody List<Game> games) {
		service.updateGamesInActiveRound(seasonId, games);		
		return ResponseEntity.ok().build();
	}
	
	@PutMapping(path = "/start/{seasonId}")
	public ResponseEntity<?> startSeason(@PathVariable long seasonId) {
		service.startSeason(seasonId);		
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping(path = "/close/{seasonId}")
	public ResponseEntity<?> closeSeason(@PathVariable long seasonId) {
		service.closeSeason(seasonId);		
		return ResponseEntity.ok().build();
	}
	
	@PostMapping(path = "/nextRound/{seasonId}")
	public ResponseEntity<Integer> goToNextRound(@PathVariable long seasonId) {
		service.goToNextRound(seasonId);		
		return ResponseEntity.ok().build();
	}
}
