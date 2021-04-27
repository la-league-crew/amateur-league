package com.league.service.impl;

import com.league.api.v1.mapper.LeagueMapper;
import com.league.api.v1.mapper.RoundMapper;
import com.league.api.v1.model.LeagueDto;
import com.league.domain.League;
import com.league.repositories.LeagueRepository;
import com.league.repositories.RoundRepository;
import com.league.service.LeagueService;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class LeagueServiceImpl implements LeagueService {

    private final LeagueRepository leagueRepository;
    private final LeagueMapper leagueMapper;

    public LeagueServiceImpl(LeagueRepository leagueRepository, LeagueMapper leagueMapper) {
        this.leagueRepository = leagueRepository;
        this.leagueMapper = leagueMapper;
    }

    @Override
    public Set<LeagueDto> findAll() {
        return leagueRepository.findAll()
                .stream()
                .map(leagueMapper::leagueToLeagueDto)
                .collect(Collectors.toSet());
    }

    @Override
    public LeagueDto save(LeagueDto leagueDto) {
        League savedLeague= leagueMapper.leagueDtoToLeague(leagueDto);
        return leagueMapper.leagueToLeagueDto(leagueRepository.save(savedLeague));
    }

    @Override
    public LeagueDto findById(Long aLong) {
        return leagueMapper.leagueToLeagueDto(leagueRepository.findById(aLong).orElse(null));
    }

    @Override
    public void deleteById(Long aLong) {
        leagueRepository.deleteById(aLong);
    }
}
