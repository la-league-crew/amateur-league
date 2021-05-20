package com.league.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.league.repo.ClubRepresentationRepo;

@RestController
@RequestMapping(path = "/reps")
public class ClubRepresentationController {

	@Autowired
	private ClubRepresentationRepo repo;
	
	@GetMapping
	public ResponseEntity<?> get() {
		return ResponseEntity.ok(repo.findAll());
	}
}
