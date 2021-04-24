package com.league.api.v1.mapper;

import com.league.api.v1.model.LeagueDto;
import com.league.api.v1.model.TeamDto;
import com.league.api.v1.model.TeamInfoDto;
import com.league.domain.League;
import com.league.domain.Team;
import com.league.domain.TeamInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TeamMapperTest {

    TeamMapper mapper;

    @BeforeEach
    void setUp() {
        mapper= TeamMapper.INSTANCE;
    }

    @Test
    void teamToTeamDto() {

//        Given
        Team team= new Team();
        team.setId(1L);
        team.setTeamTitle("Red Star");
        team.setTournamentNumber(3);
        TeamInfo teamInfo= new TeamInfo();
        teamInfo.setId(1L);
        teamInfo.setCountry("Serbia");
        team.setTeamInfo(teamInfo);
        League league= new League();
        league.setId(2L);
        team.setLeague(league);

//        When
        TeamDto teamDto= mapper.teamToTeamDto(team);

//        Then
        Assertions.assertEquals(teamDto.getId(),team.getId());
        Assertions.assertEquals(teamDto.getTeamInfoId(), teamInfo.getId());
        Assertions.assertEquals(teamDto.getLeagueId(), league.getId());


    }

    @Test
    void teamDtoToTeam() {
//        Given
        TeamDto teamDto= new TeamDto();
        teamDto.setId(1L);
        teamDto.setTeamTitle("Red Star");
        teamDto.setTournamentNumber(3);
        LeagueDto leagueDto= new LeagueDto();
        leagueDto.setId(3L);
        teamDto.setLeagueId(leagueDto.getId());
        TeamInfoDto teamInfoDto= new TeamInfoDto();
        teamInfoDto.setId(4L);
        teamDto.setTeamInfoId(teamInfoDto.getId());

//        When
        Team team= mapper.teamDtoToTeam(teamDto);

//        Then
        Assertions.assertEquals(team.getId(), teamDto.getId());
        Assertions.assertEquals(team.getLeague().getId(), teamDto.getLeagueId());
        Assertions.assertEquals(team.getTeamInfo().getId(), teamDto.getTeamInfoId());

    }
}