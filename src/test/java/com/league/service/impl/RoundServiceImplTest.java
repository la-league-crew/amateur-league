package com.league.service.impl;

import com.league.api.v1.mapper.RoundMapper;
import com.league.api.v1.model.RoundDto;
import com.league.domain.Match;
import com.league.domain.Round;
import com.league.repositories.RoundRepository;
import com.league.service.RoundService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.*;

class RoundServiceImplTest {

    RoundService roundService;

    RoundMapper roundMapper;

    @Mock
    RoundRepository roundRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        roundMapper= RoundMapper.INSTANCE;
        roundService= new RoundServiceImpl(roundRepository, roundMapper);
    }

    @Test
    void findAll() {
//        Given
        Round round1= new Round();
        round1.setId(1L);
        Round round2= new Round();
        round2.setId(2L);
        List<Round> roundList= new ArrayList<>();
        roundList.add(round1);
        roundList.add(round2);

        Mockito.when(roundRepository.findAll()).thenReturn(roundList);

//        When
        Set<RoundDto> roundDtoSet= roundService.findAll();

//        Then
        Assertions.assertEquals(roundDtoSet.size(),2);

    }

    @Test
    void testMatchInsideRound(){
//        Given
        Round round1= new Round();
        round1.setId(1L);
        Match match1= new Match();
        match1.setId(5L);
        Match match2= new Match();
        match2.setId(8L);
        round1.getMatches().add(match1);
        round1.getMatches().add(match2);

        Mockito.when(roundRepository.findAll()).thenReturn(Collections.singletonList(round1));

//        When
        Set<RoundDto> roundDtoSet= roundService.findAll();

//        Then
        Assertions.assertEquals(roundDtoSet.stream()
                .findFirst()
                .get().getMatchDtoList().size(), 2);
        Assertions.assertEquals(roundDtoSet.stream()
                .findFirst()
                .get().getMatchDtoList()
                .stream()
                .filter(matchDto -> matchDto.getId()== 5)
                .findFirst()
                .get().getId(),5);
    }

    @Test
    void save() {
//        Given
        Round round= new Round();
        round.setRoundNo(3);
        Match match= new Match();
        match.setId(1L);
        round.getMatches().add(match);

        Mockito.when(roundRepository.save(Mockito.any(Round.class))).thenReturn(round);
//        When
        RoundDto roundDto= roundService.save(roundMapper.roundToRoundDto(round));

//        Then
        Assertions.assertEquals(roundDto.getRoundNo(), 3);
        Assertions.assertEquals(roundDto.getMatchDtoList().stream().findFirst().get().getId(), 1);

    }

    @Test
    void findById() {
//        Given
        long roundId= 3L;
        Round round= new Round();
        round.setId(roundId);

        Mockito.when(roundRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(round));

//        When
        RoundDto roundDto= roundService.findById(roundId);

//        Then
        Assertions.assertEquals(roundDto.getId(), roundId);
    }

    @Test
    void deleteById() {
//        Given
        long roundId= 3L;

//        When
        roundService.deleteById(roundId);

//        Then
        Mockito.verify(roundRepository, Mockito.times(1)).deleteById(Mockito.anyLong());
    }
}