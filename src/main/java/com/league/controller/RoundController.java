package com.league.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.league.entity.Game;
import com.league.entity.Round;
import com.league.repo.RoundRepo;

@RestController
@RequestMapping(path = "/round")
public class RoundController {

	@Autowired
	private RoundRepo repo;
	
	@GetMapping
	public ResponseEntity<?> get() {
		return ResponseEntity.ok(repo.findAll());
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<List<Game>> getGames() {
		Round round = repo.findAll().get(0);
		List<Game> gms = round.getGames();
		System.out.println(gms.size());
		gms.forEach(g -> System.out.println(g.toString()));
		return ResponseEntity.ok(gms);
	}
}
