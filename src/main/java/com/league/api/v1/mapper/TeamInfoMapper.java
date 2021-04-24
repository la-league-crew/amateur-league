package com.league.api.v1.mapper;

import com.league.api.v1.model.TeamInfoDto;
import com.league.domain.TeamInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TeamInfoMapper {

    final TeamInfoMapper INSTANCE= Mappers.getMapper(TeamInfoMapper.class);

    @Mapping(source = "id", target = "id")
    TeamInfoDto teamInfoToTeamInfoDto(TeamInfo teamInfo);

    @Mapping(source = "id", target = "id")
    TeamInfo teamInfoDtoToTeamInfo(TeamInfo teamInfo);
}
