package com.league.api.v1.mapper;

import com.league.api.v1.model.LeagueDto;
import com.league.domain.League;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface LeagueMapper
{
    final LeagueMapper INSTANCE= Mappers.getMapper(LeagueMapper.class);

    @Mapping(source = "id", target = "id")
    LeagueDto leagueToLeagueDto(League league);

    @Mapping(source = "id", target = "id")
    League leagueDtoToLeague(LeagueDto leagueDto);
}
