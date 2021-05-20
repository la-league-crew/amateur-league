package com.league.entity;

import java.util.ArrayList;
import java.util.List;

//test class
public class RoundRepresentation {

	private List<Game> games;
	
	public RoundRepresentation() {
		games = new ArrayList<>();
	}

	public RoundRepresentation(List<Game> games) {
		this.games = games;
	}

	public List<Game> getGames() {
		return games;
	}

	public void setGames(List<Game> games) {
		this.games = games;
	}
	
	public void addGame(Game game) {
		this.games.add(game);
	}

	public void toStringZ() {
		games.forEach(g -> System.out.println(g.toString()));
	}
		
	
}
