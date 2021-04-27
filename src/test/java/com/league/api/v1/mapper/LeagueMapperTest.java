package com.league.api.v1.mapper;

import com.league.api.v1.model.LeagueDto;
import com.league.api.v1.model.RoundDto;
import com.league.api.v1.model.TeamDto;
import com.league.domain.League;
import com.league.domain.Round;
import com.league.domain.Season;
import com.league.domain.Team;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

class LeagueMapperTest {

    LeagueMapper leagueMapper;

    @BeforeEach
    void setUp() {
        leagueMapper= LeagueMapper.INSTANCE;
    }

    @Test
    void leagueToLeagueDto() {
//        Given
        League league= new League();
        league.setId(1L);
        Season season= new Season();
        season.setId(4L);
        league.setSeason(season);
        Set<Team> teamSet= new HashSet<>();
        teamSet.add(new Team());
        league.setTeams(teamSet);
        Set<Round> roundSet= new HashSet<>();
        roundSet.add(new Round());
        league.setRounds(roundSet);

//        When
        LeagueDto leagueDto= leagueMapper.leagueToLeagueDto(league);

//        Then
        Assertions.assertEquals(leagueDto.getId(), league.getId());
        Assertions.assertEquals(leagueDto.getRounds().size(), league.getRounds().size());
        Assertions.assertEquals(leagueDto.getTeams().size(), league.getTeams().size());
        Assertions.assertEquals(leagueDto.getSeasonId(), league.getSeason().getId());
    }

    @Test
    void leagueDtoToLeague() {
//        Given
        LeagueDto leagueDto= new LeagueDto();
        leagueDto.setId(1L);
        Set<RoundDto> roundDtoSet= new HashSet<>();
        roundDtoSet.add(new RoundDto());
        roundDtoSet.add(new RoundDto());
        leagueDto.setRounds(roundDtoSet);
        Set<TeamDto> teamDtoSet= new HashSet<>();
        teamDtoSet.add(new TeamDto());
        leagueDto.setTeams(teamDtoSet);
        leagueDto.setSeasonId(2L);

//        When
        League league= leagueMapper.leagueDtoToLeague(leagueDto);

//        Then
        Assertions.assertEquals(league.getId(), leagueDto.getId());
        Assertions.assertEquals(league.getRounds().size(), leagueDto.getRounds().size());
        Assertions.assertEquals(league.getTeams().size(), leagueDto.getTeams().size());
        Assertions.assertEquals(league.getSeason().getId(), leagueDto.getSeasonId());
    }

    @Test
    void testLeagueToLeagueDtoWithoutId(){
//        Given
        League league= new League();
        league.setTitle("Some league");

//        When
        LeagueDto leagueDto= leagueMapper.leagueToLeagueDto(league);

//        Then
        Assertions.assertNotEquals(leagueDto.getId(),1);
    }
}