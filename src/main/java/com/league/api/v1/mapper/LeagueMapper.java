package com.league.api.v1.mapper;

import com.league.api.v1.model.LeagueDto;
import com.league.domain.League;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface LeagueMapper
{
    final LeagueMapper INSTANCE= Mappers.getMapper(LeagueMapper.class);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "season.id", target = "seasonId")
    })
    LeagueDto leagueToLeagueDto(League league);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "seasonId", target = "season.id")
    })
    League leagueDtoToLeague(LeagueDto leagueDto);
}
