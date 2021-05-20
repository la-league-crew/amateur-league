package com.league.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.league.repo.GameRepo;

@RestController
@RequestMapping(path = "/game")
public class GameController {

	@Autowired
	private GameRepo repo;
	
	@GetMapping
	public ResponseEntity<?> get() {
		return ResponseEntity.ok(repo.findAll());
	}
}
