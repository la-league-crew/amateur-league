package com.league.repo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.league.entity.Game;
import com.league.entity.Round;
import com.league.entity.Season;
import com.league.service.SeasonService;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
@TestInstance(Lifecycle.PER_CLASS)
@Transactional
class RepoTest {

	
	@Autowired
	private RoundRepo roundRepo;
	
	@Autowired
	private SeasonRepo seasonRepo;
	
	@Autowired
	private GameRepo gameRepo;
	
	@Autowired
	private SeasonService service;
	
	@BeforeAll
	void init() {
		
	}
	
	@Test
	@Order(1)
	void test() {
		
//		Game game = new Game(1L, "bla", 2L, "znju");
//		Round round = new Round();
//		round.addGame(game);
//		//roundRepo.save(round);
//		
//		Season season = new Season();
//		season.addRound(round);
//		seasonRepo.save(season);
//		
//		assertEquals(1, roundRepo.findAll().size());
//		assertEquals(1, gameRepo.findAll().size());
//		assertEquals(2, seasonRepo.findAll().size());
	}

	@Test
	@Order(2)
	void testSecond() {
		service.makeRounds(1L);
		
		Season season = service.findOne(1L).get();
		List<Round> seRounds = season.getRounds();
		assertEquals(18, seRounds.size());
		
		List<Game> games = seRounds.get(1).getGames();
		assertEquals(5, games.size());
	}
}
