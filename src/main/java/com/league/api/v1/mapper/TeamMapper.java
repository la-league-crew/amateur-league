package com.league.api.v1.mapper;

import com.league.api.v1.model.TeamDto;
import com.league.domain.Team;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TeamMapper
{
    final TeamMapper INSTANCE= Mappers.getMapper(TeamMapper.class);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "teamInfo.id", target = "teamInfoId"),
            @Mapping(source = "league.id", target = "leagueId")
    })
    TeamDto teamToTeamDto(Team team);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "teamInfoId", target = "teamInfo.id"),
            @Mapping(source = "leagueId", target = "league.id")
    })
    Team teamDtoToTeam(TeamDto teamDto);

}
