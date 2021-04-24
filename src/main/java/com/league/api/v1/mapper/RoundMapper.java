package com.league.api.v1.mapper;

import com.league.api.v1.model.RoundDto;
import com.league.domain.Round;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RoundMapper {

    final RoundMapper INSTANCE= Mappers.getMapper(RoundMapper.class);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "league.id", target = "leagueId"),
            @Mapping(source = "matches", target = "matchDtoList")
    })
    RoundDto roundToRoundDto(Round round);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "leagueId", target = "league.id"),
            @Mapping(source = "matchDtoList", target = "matches")
    })
    Round roundDtoToRound(RoundDto roundDto);
}
