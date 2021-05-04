package com.league.service.impl;

import com.league.api.v1.mapper.LeagueMapper;
import com.league.api.v1.model.LeagueDto;
import com.league.domain.League;
import com.league.domain.Round;
import com.league.repositories.LeagueRepository;
import com.league.service.LeagueService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.*;

@WebMvcTest
@ExtendWith(SpringExtension.class)
class LeagueServiceImplTest {

    LeagueService leagueService;

    @Mock
    LeagueRepository leagueRepository;

    LeagueMapper leagueMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        leagueMapper= LeagueMapper.INSTANCE;
        leagueService= new LeagueServiceImpl(leagueRepository, leagueMapper);
    }

    @Test
    void findAll() {
//        Given
        League league1= new League();
        league1.setId(1L);
        League league2= new League();
        league2.setId(2L);
        List<League> leagueSet= new ArrayList<>();
        leagueSet.add(league1);
        leagueSet.add(league1);

//        When
        Mockito.when(leagueRepository.findAll()).thenReturn(leagueSet);

        Set<LeagueDto> leagueDtoSet= leagueService.findAll();

//        Then
        Assertions.assertEquals(leagueDtoSet.size(), leagueSet.size());
    }

    @Test
    void save() {
//        Given
        League league= new League();
        league.setId(1L);
        Round round1= new Round();
        round1.setRoundNo(4);
        Round round2= new Round();
        Set<Round> roundSet= new HashSet<>();
        roundSet.add(round1);
        roundSet.add(round2);
        league.setRounds(roundSet);

//        When
        Mockito.when(leagueRepository.save(Mockito.any(League.class)))
                .thenReturn(league);
        LeagueDto leagueDto= leagueService.save(leagueMapper.leagueToLeagueDto(league));

//        Then
        Assertions.assertEquals(leagueDto.getId(), league.getId());
        Assertions.assertEquals(leagueDto.getRounds().size(), 2);
        Assertions.assertEquals(leagueDto.getRounds()
                .stream()
                .filter(roundDto -> roundDto.getRoundNo()== 4)
                .findFirst().get().getRoundNo(), 4);
    }

    @Test
    void findById() {
//        Given
        League league= new League();
        league.setId(2L);

        Mockito.when(leagueRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(league));

//        When
        LeagueDto leagueDto= leagueService.findById(2L);

//        Then
        Assertions.assertEquals(leagueDto.getId(), 2);
    }

    @Test
    void deleteById() {
//        Given
        long leagueId= 1L;

//        When
        leagueService.deleteById(leagueId);

//        Then
        Mockito.verify(leagueRepository, Mockito.times(1)).deleteById(Mockito.anyLong());
    }

}