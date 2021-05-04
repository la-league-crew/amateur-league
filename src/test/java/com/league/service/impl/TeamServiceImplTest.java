package com.league.service.impl;

import com.league.api.v1.mapper.TeamMapper;
import com.league.api.v1.model.TeamDto;
import com.league.domain.League;
import com.league.domain.Team;
import com.league.domain.TeamInfo;
import com.league.repositories.TeamRepository;
import com.league.service.TeamService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

class TeamServiceImplTest {

    TeamService teamService;

    @Mock
    TeamRepository teamRepository;

    TeamMapper teamMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        teamMapper= TeamMapper.INSTANCE;
        teamService= new TeamServiceImpl(teamRepository, teamMapper);
    }

    @Test
    void findAll() {
//        Given
        List<Team> teamList= new ArrayList<>();
        Team team1= new Team();
        team1.setId(1L);
        Team team2= new Team();
        team2.setId(2L);
        teamList.add(team1);
        teamList.add(team2);

        Mockito.when(teamRepository.findAll()).thenReturn(teamList);

//        When
        Set<TeamDto> teamSet= teamService.findAll();

//        Then
        Assertions.assertEquals(teamSet.size(), teamList.size());
    }

    @Test
    void save() {
//        Given
        Team team= new Team();
        team.setId(1L);
        League league= new League();
        league.setId(10L);
        team.setLeague(league);
        TeamInfo teamInfo= new TeamInfo();
        teamInfo.setId(2L);
        team.setTeamInfo(teamInfo);

        Mockito.when(teamRepository.save(Mockito.any(Team.class)))
                .thenReturn(team);

//        When
        TeamDto savedTeamDto= teamService.save(teamMapper.teamToTeamDto(team));

//        Then
        Assertions.assertEquals(savedTeamDto.getId(), 1);
        Assertions.assertEquals(savedTeamDto.getLeagueId(), 10);
        Assertions.assertEquals(savedTeamDto.getTeamInfoId(), 2);
    }

    @Test
    void findById() {
//        Given
        Team team= new Team();
        team.setId(2L);
        League league= new League();
        league.setId(5L);
        team.setLeague(league);

        Mockito.when(teamRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(team));
//        When
        TeamDto founded= teamService.findById(Mockito.anyLong());

//        Then
        Assertions.assertEquals(founded.getId(), 2);
        Assertions.assertEquals(founded.getLeagueId(), 5);
    }

    @Test
    void deleteById() {
//        Given
        long teamId= 1L;

//        When
        teamService.deleteById(teamId);

//        Then
        Mockito.verify(teamRepository, Mockito.times(1)).deleteById(Mockito.anyLong());
    }

    @Test
    void findByTeamTitleLike() {
//        Given
        Team team= new Team();
        team.setId(1L);
        team.setTeamTitle("FC Juventus");

        Mockito.when(teamRepository.findByTeamTitleLike(Mockito.anyString())).thenReturn(Optional.of(team));

//        When
        TeamDto teamDto= teamService.findByTeamTitleLike("tus");

//        Then
        Assertions.assertEquals(teamDto.getId(), 1);
        Assertions.assertEquals(teamDto.getTeamTitle(),"FC Juventus");
    }
}