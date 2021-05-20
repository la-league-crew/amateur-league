package com.league.service;

import java.util.List;

import com.league.entity.Game;
import com.league.entity.Round;
import com.league.entity.Season;

public interface SeasonService extends CrudService<Season>{

	void addClubToSeason(Long seasonId, Long clubId) throws IllegalArgumentException; //, String clubName
	
	void deleteClubFromSeason(Long seasonId, Long clubId) throws IllegalArgumentException;
	
	void makeRounds(Long seasonId) throws IllegalArgumentException;
	
	List<Round> getRounds(Long seasonId) throws IllegalArgumentException;
		
	void startSeason(Long seasonId) throws IllegalArgumentException; 
	
	void closeSeason(Long seasonId) throws IllegalArgumentException;
	
	void updateGamesInActiveRound(Long seasonId, List<Game> games) throws IllegalArgumentException;
	
	int goToNextRound(Long seasonId);
}
