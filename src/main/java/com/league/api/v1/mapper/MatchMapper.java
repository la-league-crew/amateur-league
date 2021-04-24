package com.league.api.v1.mapper;

import com.league.api.v1.model.MatchDto;
import com.league.domain.Match;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MatchMapper {

    final MatchMapper INSTANCE= Mappers.getMapper(MatchMapper.class);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "round.id", target = "roundId")
    })
    MatchDto matchToMatchDto(Match match);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "roundId", target = "round.id")
    })
    Match matchDtoToMatch(MatchDto matchDto);
}
