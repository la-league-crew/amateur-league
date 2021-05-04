package com.league.service.impl;

import com.league.api.v1.mapper.TeamMapper;
import com.league.api.v1.model.TeamDto;
import com.league.domain.Team;
import com.league.repositories.TeamRepository;
import com.league.service.TeamService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;
    private final TeamMapper teamMapper;

    public TeamServiceImpl(TeamRepository teamRepository, TeamMapper teamMapper) {
        this.teamRepository = teamRepository;
        this.teamMapper = teamMapper;
    }

    @Override
    public Set<TeamDto> findAll() {
        return teamRepository.findAll().stream()
                .map(teamMapper::teamToTeamDto)
                .collect(Collectors.toSet());
    }

    @Override
    public TeamDto save(TeamDto teamDto) {
        Team team= teamMapper.teamDtoToTeam(teamDto);
        return teamMapper.teamToTeamDto(teamRepository.save(team));
    }

    @Override
    public TeamDto findById(Long aLong) {
        return teamMapper.teamToTeamDto(teamRepository.findById(aLong).orElse(null));
    }

    @Override
    public TeamDto findByTeamTitleLike(String name) {
        Optional<Team> teamOptional= teamRepository.findByTeamTitleLike("%"+name+"%");
        return teamOptional.map(teamMapper::teamToTeamDto).orElse(null);
    }

    @Override
    public void deleteById(Long aLong) {
        teamRepository.deleteById(aLong);
    }
}
