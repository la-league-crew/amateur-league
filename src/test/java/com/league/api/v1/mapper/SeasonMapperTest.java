package com.league.api.v1.mapper;

import com.league.api.v1.model.LeagueDto;
import com.league.api.v1.model.SeasonDto;
import com.league.domain.League;
import com.league.domain.Round;
import com.league.domain.Season;
import com.league.domain.Team;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

class SeasonMapperTest {

    SeasonMapper seasonMapper;

    @BeforeEach
    void setUp() {
        seasonMapper= SeasonMapper.INSTANCE;
    }

    @Test
    void seasonToSeasonDto() {
//        Given
        Season season= new Season();
        season.setId(1L);
        season.setStartDate(LocalDate.now());

        Set<League> leagueSet= new HashSet<>();
        League league= new League();
        league.setId(4L);
        Round round= new Round();
        round.setId(1L);
        Set<Round> roundSet= new HashSet<>();
        roundSet.add(round);
        league.setRounds(roundSet);
        Team team1= new Team();
        team1.setId(10L);
        Team team2= new Team();
        team2.setId(11L);
        Set<Team> teamSet= new HashSet<>();
        teamSet.add(team1);
        teamSet.add(team2);
        league.setTeams(teamSet);
        leagueSet.add(league);
        season.setLeagues(leagueSet);

//        When
        SeasonDto seasonDto= seasonMapper.seasonToSeasonDto(season);

//        Then
        Assertions.assertEquals(seasonDto.getId(), season.getId());
        Assertions.assertEquals(seasonDto.getStartDate(), season.getStartDate());
        Assertions.assertEquals(seasonDto.getLeagues().size(), season.getLeagues().size());
        Assertions.assertEquals(seasonDto.getLeagues().stream().findFirst().get().getTeams().size()
                ,season.getLeagues().stream().findFirst().get().getTeams().size());
    }

    @Test
    void seasonDtoToSeason() {
//        Given
        SeasonDto seasonDto= new SeasonDto();
        seasonDto.setId(1L);
        seasonDto.setStartDate(LocalDate.now());

        Set<LeagueDto> leagueDtoSet= new HashSet<>();
        leagueDtoSet.add(new LeagueDto());
        seasonDto.setLeagues(leagueDtoSet);

//        When
        Season season= seasonMapper.seasonDtoToSeason(seasonDto);

//        Then
        Assertions.assertEquals(season.getId(), seasonDto.getId());
        Assertions.assertEquals(season.getStartDate(), seasonDto.getStartDate());
        Assertions.assertEquals(season.getLeagues().size(), seasonDto.getLeagues().size());

    }
}