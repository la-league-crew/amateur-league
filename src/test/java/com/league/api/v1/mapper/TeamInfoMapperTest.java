package com.league.api.v1.mapper;

import com.league.api.v1.model.TeamInfoDto;
import com.league.domain.TeamInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class TeamInfoMapperTest {

    TeamInfoMapper teamInfoMapper;

    @BeforeEach
    void setUp() {
        teamInfoMapper= TeamInfoMapper.INSTANCE;
    }

    @Test
    void teamInfoToTeamInfoDto() {
//        Given
        TeamInfo teamInfo= new TeamInfo();
        teamInfo.setId(1L);
        teamInfo.setCity("Belgrade");
        teamInfo.setCountry("Serbia");
        teamInfo.setStadiumName("Enfield");

//        When
        TeamInfoDto teamInfoDto= teamInfoMapper.teamInfoToTeamInfoDto(teamInfo);

//        Then
        Assertions.assertEquals(teamInfoDto.getId(), teamInfo.getId());
        Assertions.assertEquals(teamInfoDto.getCountry(), teamInfo.getCountry());
    }

    @Test
    void teamInfoDtoToTeamInfo() {
//        Given
        TeamInfoDto teamInfoDto= new TeamInfoDto();
        teamInfoDto.setId(2L);
        teamInfoDto.setCountry("Spain");

//        When
        TeamInfo teamInfo= teamInfoMapper.teamInfoDtoToTeamInfo(teamInfoDto);

//        Then
        Assertions.assertEquals(teamInfo.getId(), teamInfoDto.getId());
        Assertions.assertEquals(teamInfo.getCountry(), teamInfoDto.getCountry());
    }
}