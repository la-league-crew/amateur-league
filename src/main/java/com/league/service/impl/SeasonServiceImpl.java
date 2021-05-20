package com.league.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.league.entity.Club;
import com.league.entity.ClubRepresentation;
import com.league.entity.Game;
import com.league.entity.Round;
import com.league.entity.Season;
import com.league.repo.ClubRepo;
import com.league.repo.ClubRepresentationRepo;
import com.league.repo.GameRepo;
import com.league.repo.SeasonRepo;
import com.league.service.SeasonService;

@Service
@Transactional
public class SeasonServiceImpl implements SeasonService {

	@Autowired
	private SeasonRepo seasonRepo;

	@Autowired
	private GameRepo gameRepo;
	
	@Autowired
	private ClubRepo clubRepo;
	
	@Autowired
	private ClubRepresentationRepo repRepo;
	
	@Override
	public Optional<Season> findOne(Long id) {
		return seasonRepo.findById(id);
	}

	@Override
	public List<Season> findAll() {
		return seasonRepo.findAll();
	}

	@Override
	public Season save(Season dto) {
		return seasonRepo.save(dto);
	}

	@Override
	public Season update(Long id, Season dto) {
		Optional<Season> optional = seasonRepo.findById(id);
		if (!optional.isPresent()) {
			throw new IllegalArgumentException();
		}
		Season season = optional.get();
		// TODO
		return null;
	}

	@Override
	public void delete(Long id) {
		Optional<Season> optional = seasonRepo.findById(id);
		if (!optional.isPresent()) {
			throw new IllegalArgumentException();
		}
		seasonRepo.deleteById(id);
	}

	@Override
	public void setAvaialbleTo(long id, boolean available) {
		Optional<Season> optional = seasonRepo.findById(id);
		if (!optional.isPresent()) {
			throw new IllegalArgumentException();
		}
		Season season = optional.get();
		season.setAvailable(available);
	}

	@Override
	public void addClubToSeason(Long seasonId, Long clubId) { //, String clubName) { //Club newClub) {
		Optional<Season> optSeason = seasonRepo.findById(seasonId);
		if (!optSeason.isPresent()) {
			throw new IllegalArgumentException("There is no such season");
		}
		Season season = optSeason.get();
		
		Optional<Club> optClub = clubRepo.findById(seasonId);
		if (!optClub.isPresent()) {
			throw new IllegalArgumentException("There is no such club");
		}
		Club club = optClub.get();

		List<ClubRepresentation> reps = season.getRepresentations();
		boolean isPresent = reps.stream().filter(rep -> rep.getClubId().equals(clubId)).findAny().isPresent();
		if (isPresent) {
			throw new IllegalArgumentException("The value is already in the list.");
		}

		season.addClubRepresentation(new ClubRepresentation(clubId, club.getName()));
		// TODO add club representation
		// check if there is a need for season entity to has list of clubs and list of
		// reps
	}

	@Override
	public void deleteClubFromSeason(Long seasonId, Long clubId) throws IllegalArgumentException {
		Optional<Season> optional = seasonRepo.findById(seasonId);
		if (!optional.isPresent()) {
			throw new IllegalArgumentException();
		}
		Season season = optional.get();

		List<ClubRepresentation> reps = season.getRepresentations();
		boolean isDeleted = reps.removeIf(club -> club.getId().equals(clubId));
		if (!isDeleted) {
			throw new IllegalArgumentException();
		}
		// TODO delete club representation
	}

	@Override
	public void makeRounds(Long seasonId) throws IllegalArgumentException {
		Optional<Season> optional = seasonRepo.findById(seasonId);
		if (!optional.isPresent()) {
			throw new IllegalArgumentException();
		}
		Season season = optional.get();

		List<ClubRepresentation> reps = season.getRepresentations();
		if (reps.size() % 2 != 0) {
			// throws exception if num of clubs is not even
			throw new IllegalArgumentException();
		}

		Collections.shuffle(reps);
		for (int i = 0; i < reps.size(); i++) {
			reps.get(i).setInitialRank(i);
		}
		//reps.stream().sorted(Comparator.comparing(ClubRepresentation::getInitialRank)).collect(Collectors.toList());
		
		int n = reps.size();
		int roundsNum = n-1;
		int m, k; //, l = 1; 
		List<Round> roundList = new ArrayList<>();
		List<Round> reverseRoundList = new ArrayList<>();
		for (int i = 0; i < roundsNum; i++) {
			m = 0;
			k = n;
			k--;
			Round round = new Round();			
			Round reverseRound = new Round();
			while (m < k) {
				//System.out.println(sortedReps.get(m).id + " " + sortedReps.get(n).id);
				ClubRepresentation home = reps.get(m);
				ClubRepresentation away = reps.get(k);
				
				Game game = new Game(home.getClubId(), home.getClubName(), away.getClubId(), away.getClubName());
				round.addGame(game);
				Game reverseGame = new Game(away.getClubId(), away.getClubName(), home.getClubId(), home.getClubName());
				reverseRound.addGame(reverseGame);
				m++;
				k--;
				
				game.equals(reverseGame);
			}
			roundList.add(round);
			reverseRoundList.add(reverseRound);
			
			rotateReal(reps);
		}

		season.addRoundList(roundList);
		season.addRoundList(reverseRoundList);
	}

	@Override
	public List<Round> getRounds(Long seasonId) throws IllegalArgumentException {
		Optional<Season> optional = seasonRepo.findById(seasonId);
		if (!optional.isPresent()) {
			throw new IllegalArgumentException();
		}
		Season season = optional.get();
		
		return season.getRounds();
	}

	@Override
	public void updateGamesInActiveRound(Long seasonId, List<Game> games) throws IllegalArgumentException {
		Optional<Season> optional = seasonRepo.findById(seasonId);
		if (!optional.isPresent()) {
			throw new IllegalArgumentException();
		}
		Season season = optional.get();
		games.forEach(g -> {
			Optional<Game> opt = gameRepo.findById(g.getId());
			if (!opt.isPresent()) {
				throw new IllegalArgumentException("There is no game with such id");
			}
			if (g.getSeasonId() != season.getId()) {
				throw new IllegalArgumentException("Game's field seasonId is not matching with id of the actual season");
			}
			
			Game game = opt.get();
			game.setHomeGoals(g.getHomeGoals());
			game.setAwayGoals(g.getAwayGoals());
		});
	}
	
	@Override
	public void startSeason(Long seasonId) throws IllegalArgumentException {
		Optional<Season> optional = seasonRepo.findById(seasonId);
		if (!optional.isPresent()) {
			throw new IllegalArgumentException();
		}
		Season season = optional.get();
		
//		List<ClubRepresentation> reps = season.getRepresentations();
//		List<Round> rounds = season.getRounds();
//		List<Game> games = gameRepo.findBySeasonId(seasonId);
		
		final int re = season.getRepresentations().size();
		final int ro = season.getRounds().size();
		final int ga = gameRepo.findBySeasonId(seasonId).size();
		
		//checks if the number of rounds and games corresponds to the number of clubs related
		if ((ro == (re-1)*2) && 
				(ga == ro*(re/2))) {
			season.setStarted(true);
			season.setAvailable(true);
			season.setClosed(false);
			season.setActiveRoundId(0);
		} else {
			throw new IllegalArgumentException();			
		}
	}

	@Override
	public void closeSeason(Long seasonId) throws IllegalArgumentException {
		Optional<Season> optional = seasonRepo.findById(seasonId);
		if (!optional.isPresent()) {
			throw new IllegalArgumentException();
		}
		
		Season season = optional.get();
		season.setClosed(true);
		season.setAvailable(false);
		//probably you will have to add some more stuff here
	}

	@Override
	public int goToNextRound(Long seasonId) {
		Optional<Season> optional = seasonRepo.findById(seasonId);
		if (!optional.isPresent()) {
			throw new IllegalArgumentException("There is no season with such id");
		}
		Season season = optional.get();
		
		//TODO this check up should be on almost any method
		if (!season.isAvailable() 
				|| season.isClosed() 
					|| !season.isStarted()) {
			throw new IllegalArgumentException("Season is not available for some reason"); 
		}
		int roundNum = season.getActiveRoundId();
		season.setActiveRoundId(++roundNum);
		return roundNum;
	}

	// test this method
	private <T> void rotate1(List<T> list) {
		int size = list.size();
		if (size == 0)
			return;

		for (int cycleStart = 0, nMoved = 0; nMoved != size; cycleStart++) {
			T displaced = list.get(cycleStart);
			int i = cycleStart;
			do {
				if (i >= size)
					i -= size;
				displaced = list.set(i, displaced);
				nMoved++;
			} while (i != cycleStart);
		}
	}
	
	private static void rotateReal(List<ClubRepresentation> list) {
		int size = list.size();
		ClubRepresentation displaced = list.get(1);
        int i = 1;
        do {
            i++;
            if (i == size)
                i -= (size - 1);
            displaced = list.set(i, displaced);
        } while (i != 1);
	}

	

	
}
