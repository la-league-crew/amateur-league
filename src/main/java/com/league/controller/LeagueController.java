package com.league.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.league.entity.League;
import com.league.repo.LeagueRepo;

@RestController
@RequestMapping(path = "/api/league")
public class LeagueController {

	@Autowired
	private LeagueRepo repo;
	
	@GetMapping
	public ResponseEntity<?> getAllLeagues() {
	
		List<League> leagues = repo.findAll();
		return ResponseEntity.ok(leagues);
	}
}
