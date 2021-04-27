package com.league.service.impl;

import com.league.api.v1.mapper.MatchMapper;
import com.league.api.v1.model.MatchDto;
import com.league.domain.Match;
import com.league.domain.Round;
import com.league.domain.Team;
import com.league.repositories.MatchRepository;
import com.league.service.MatchService;
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

class MatchServiceImplTest {

    MatchService matchService;

    MatchMapper matchMapper;

    @Mock
    MatchRepository matchRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        matchMapper= MatchMapper.INSTANCE;
        matchService= new MatchServiceImpl(matchRepository,matchMapper);
    }

    @Test
    void findAll() {
//        Given
        Match match1= new Match();
        match1.setId(1L);
        Team host= new Team();
        host.setId(10L);
        match1.setHost(host);

        Match match2= new Match();
        match2.setId(2L);
        Team host2= new Team();
        host2.setId(11L);
        match2.setHost(host2);

        List<Match> matchList= new ArrayList<>();
        matchList.add(match1);
        matchList.add(match2);


        Mockito.when(matchRepository.findAll()).thenReturn(matchList);

//        When
        Set<MatchDto> matchDtoSet= matchService.findAll();

//        Then
        Assertions.assertEquals(matchDtoSet.size(), matchList.size());
        Assertions.assertEquals(matchDtoSet.stream()
                .filter(matchDto -> matchDto.getId()==1)
                .findFirst().get().getHostId(),
                matchList.stream()
                        .filter(match -> match.getId()==1)
                        .findFirst().get().getHost().getId());

    }

    @Test
    void save() {
//        Given
        Match match= new Match();
        match.setId(2L);
        Round round= new Round();
        round.setId(3L);
        match.setRound(round);
        Team host= new Team();
        host.setId(10L);
        match.setHost(host);

        Mockito.when(matchRepository.save(Mockito.any(Match.class))).thenReturn(match);

//        When
        MatchDto matchDto= matchService.save(new MatchDto());

//        Then
        Assertions.assertEquals(matchDto.getId(), match.getId());
        Assertions.assertEquals(matchDto.getRoundId(), match.getRound().getId());
        Assertions.assertEquals(matchDto.getHostId(), match.getHost().getId());
    }

    @Test
    void findById() {
//        Given
        Match match= new Match();
        match.setId(1L);

        Mockito.when(matchRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(match));

//        When
        MatchDto matchDto= matchService.findById(1L);

//        Then
        Assertions.assertEquals(matchDto.getId(), match.getId());
        Assertions.assertNotEquals(match.getId(),2);
    }

    @Test
    void deleteById() {
//        Given
        Match match= new Match();
        match.setId(1L);

//        When
        matchService.deleteById(match.getId());

//        Then
        Mockito.verify(matchRepository,Mockito.times(1)).deleteById(Mockito.anyLong());
    }
}