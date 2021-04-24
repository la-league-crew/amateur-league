package com.league.api.v1.mapper;

import com.league.api.v1.model.SeasonDto;
import com.league.domain.Season;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SeasonMapper {

    final SeasonMapper INSTANCE= Mappers.getMapper(SeasonMapper.class);

    @Mapping(source = "id", target = "id")
    SeasonDto seasonToSeasonDto(Season season);

    @Mapping(source = "id", target = "id")
    Season seasonDtoToSeason(SeasonDto seasonDto);
}
