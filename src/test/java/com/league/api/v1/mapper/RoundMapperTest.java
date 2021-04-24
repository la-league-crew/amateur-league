package com.league.api.v1.mapper;

import com.league.api.v1.model.MatchDto;
import com.league.api.v1.model.RoundDto;
import com.league.domain.League;
import com.league.domain.Match;
import com.league.domain.Round;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

class RoundMapperTest {

    RoundMapper roundMapper;

    @BeforeEach
    void setUp() {
        roundMapper= RoundMapper.INSTANCE;
    }

    @Test
    void roundToRoundDto() {
//        Given
        Round round= new Round();
        round.setId(1L);
        League league= new League();
        league.setId(2L);
        round.setLeague(league);
        Set<Match> matches= new HashSet<>();
        matches.add(new Match());
        round.setMatches(matches);

//        When
        RoundDto roundDto= roundMapper.roundToRoundDto(round);

//        Then
        Assertions.assertEquals(roundDto.getId(), round.getId());
        Assertions.assertEquals(roundDto.getLeagueId(), round.getLeague().getId());
        Assertions.assertEquals(roundDto.getMatchDtoList().size(), round.getMatches().size());
    }

    @Test
    void roundDtoToRound() {
//        Given
        RoundDto roundDto= new RoundDto();
        roundDto.setId(2L);
        roundDto.setLeagueId(1L);
        Set<MatchDto> matchDtoSet= new HashSet<>();
        matchDtoSet.add(new MatchDto());
        roundDto.setMatchDtoList(matchDtoSet);

//        When
        Round round= roundMapper.roundDtoToRound(roundDto);

//        Then
        Assertions.assertEquals(round.getId(), roundDto.getId());
        Assertions.assertEquals(round.getLeague().getId(), roundDto.getLeagueId());
        Assertions.assertEquals(round.getMatches().size(), 1);
    }
}