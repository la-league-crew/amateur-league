package com.league;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.league.entity.Game;

//@SpringBootTest
class AmateurLeagueApplicationTests {

	@Test
	void contextLoads() {
		Game game1 = new Game(1L, "bla", 2L , "znju");
		Game game2 = new Game(1L, "bla", 2L , "znju");
		Game game3 = null;
		
		assertTrue(game1.equals(game2));
		assertFalse(game1.equals(game3));
	}

}
