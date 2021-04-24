package com.league.api.v1.mapper;

import com.league.api.v1.model.MatchDto;
import com.league.domain.Match;
import com.league.domain.Round;
import com.league.domain.Team;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatchMapperTest {

    MatchMapper matchMapper;

    @BeforeEach
    void setUp() {
        matchMapper= MatchMapper.INSTANCE;
    }

    @Test
    void matchToMatchDto() {
//        Given
        Match match= new Match();
        match.setId(1L);
        Team host= new Team();
        host.setId(2L);
        match.setHost(host);
        Team guest= new Team();
        guest.setId(3L);
        match.setGuest(guest);
        Round round= new Round();
        round.setId(5L);
        match.setRound(round);
        match.setHostScore(2);
        match.setGuestScore(3);

//        When
        MatchDto matchDto= matchMapper.matchToMatchDto(match);

//        Then
        Assertions.assertEquals(matchDto.getId(), match.getId());
        Assertions.assertEquals(matchDto.getHostScore(), match.getHostScore());
        Assertions.assertEquals(matchDto.getGuestId(), match.getGuest().getId());
        Assertions.assertEquals(matchDto.getRoundId(), match.getRound().getId());
    }

    @Test
    void matchDtoToMatch() {
//        Given
        MatchDto matchDto= new MatchDto();
        matchDto.setId(1L);
        matchDto.setGuestId(4L);
        matchDto.setHostId(2L);
        matchDto.setRoundId(7L);
        matchDto.setGuestScore(1);

//        When
        Match match= matchMapper.matchDtoToMatch(matchDto);

//        Then
        Assertions.assertEquals(match.getId(), matchDto.getId());
        Assertions.assertEquals(match.getGuest().getId(), matchDto.getGuestId());
        Assertions.assertEquals(match.getRound().getId(), matchDto.getRoundId());
        Assertions.assertEquals(match.getGuestScore(), matchDto.getGuestScore());
    }
}