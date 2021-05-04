package com.league.service.impl;

import com.league.api.v1.mapper.LeagueMapper;
import com.league.api.v1.model.LeagueDto;
import com.league.api.v1.model.TeamDto;
import com.league.domain.League;
import com.league.repositories.LeagueRepository;
import com.league.service.LeagueService;
import org.springframework.stereotype.Service;

import java.util.*;
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

    @Override
    public boolean addTeam(Long leagueId, TeamDto teamDto) {
        Optional<League> leagueOptional= leagueRepository.findById(leagueId);

        if(leagueOptional.isPresent()){
            LeagueDto leagueDto= leagueMapper.leagueToLeagueDto(leagueOptional.get());
            if(leagueDto.isStarted())
                return false;
            else {
                leagueDto.getTeams().add(teamDto);
                leagueRepository.save(leagueMapper.leagueDtoToLeague(leagueDto));
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setTournamentDraw(Long leagueId) {

        Optional<League> leagueOptional= leagueRepository.findById(leagueId);
        if(leagueOptional.isPresent()){
            LeagueDto leagueDto= leagueMapper.leagueToLeagueDto(leagueOptional.get());
            drawGenerator(leagueDto);
            return true;
        }
        return false;
    }

    private void drawGenerator(LeagueDto leagueDto){
        int topNum= leagueDto.getTeams().size();
        Random random= new Random();

        Set<Integer> numSet= new HashSet<>();

        while (numSet.size()< topNum){
            numSet.add(random.nextInt(topNum)+1);
        }

        leagueDto.getTeams().forEach(teamDto -> {
            Optional<Integer> positionOptional= numSet.stream().findFirst();
            if (positionOptional.isPresent()){
                int position= positionOptional.get();
                teamDto.setTournamentNumber(position);
                numSet.remove(position);
            }
        });

        leagueDto.setStarted(true);
        leagueRepository.save(leagueMapper.leagueDtoToLeague(leagueDto));
    }
}
