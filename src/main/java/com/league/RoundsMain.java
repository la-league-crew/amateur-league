package com.league;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.league.entity.ClubRepresentation;
import com.league.entity.Game;
import com.league.entity.RoundRepresentation;

public class RoundsMain {

	public static void main(String[] args) {
		ClubRepresentation[] repsArray = new ClubRepresentation[] {
			new ClubRepresentation(1L, "zvezda1"),
			new ClubRepresentation(2L, "zvezda2"),
			new ClubRepresentation(3L, "zvezda3"),
			new ClubRepresentation(4L, "zvezda4"),
			new ClubRepresentation(5L, "zvezda5"),
			new ClubRepresentation(6L, "zvezda6"),
			new ClubRepresentation(7L, "zvezda7"),
			new ClubRepresentation(8L, "zvezda8"),
			new ClubRepresentation(9L, "zvezda9"),
			new ClubRepresentation(10L, "zvezda10")
		};
		List<ClubRepresentation> reps = Arrays.asList(repsArray);
		int n = reps.size();

		Collections.shuffle(reps);
		//reps.forEach(System.out::println);

		System.out.println("bla");
		
		for (int i = 0; i < reps.size(); i++) {
			reps.get(i).setInitialRank(i);
		}
		
		reps.forEach(s -> System.out.println(s.toString()));
		
		System.out.println();
		
		int roundsNum = n-1;
		int m, k; //, l = 1; 
		List<RoundRepresentation> roundList = new ArrayList<>();
		List<RoundRepresentation> reverseRoundList = new ArrayList<>();
		for (int i = 0; i < roundsNum; i++) {
			m = 0;
			k = reps.size();
			k--;
			RoundRepresentation round = new RoundRepresentation();			
			RoundRepresentation reverseRound = new RoundRepresentation();
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
			}
			roundList.add(round);
			System.out.println("regular");
			round.getGames().forEach(g -> System.out.println(g.toString()));
			
			reverseRoundList.add(reverseRound);
			System.out.println("reverse");
			reverseRound.getGames().forEach(g -> System.out.println(g.toString()));
			rotateReal(reps);
			//System.out.println(l++);
			System.out.println();
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

//List<ClubRepresentation> sortedReps = reps.stream()
//.sorted(Comparator.comparingInt(ClubRepresentation::getInitialRank))
//.collect(Collectors.toList());
