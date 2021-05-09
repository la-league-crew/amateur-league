package com.league.service.impl;

import com.league.api.v1.mapper.RoundMapper;
import com.league.api.v1.model.LeagueDto;
import com.league.api.v1.model.MatchDto;
import com.league.api.v1.model.RoundDto;
import com.league.api.v1.model.TeamDto;
import com.league.domain.Round;
import com.league.repositories.RoundRepository;
import com.league.service.RoundService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoundServiceImpl implements RoundService {

    private final RoundRepository roundRepository;
    private final RoundMapper roundMapper;

    public RoundServiceImpl(RoundRepository roundRepository, RoundMapper roundMapper) {
        this.roundRepository = roundRepository;
        this.roundMapper = roundMapper;
    }

    @Override
    public Set<RoundDto> findAll() {
        return roundRepository.findAll()
                .stream()
                .map(roundMapper::roundToRoundDto)
                .collect(Collectors.toSet());
    }

    @Override
    public RoundDto save(RoundDto roundDto) {
        Round round= roundMapper.roundDtoToRound(roundDto);
        return roundMapper.roundToRoundDto(roundRepository.save(round));
    }

    @Override
    public RoundDto update(Long aLong, RoundDto roundDto) {
        return null;
    }

    @Override
    public RoundDto findById(Long aLong) {
        return roundMapper.roundToRoundDto(roundRepository.findById(aLong).orElse(null));
    }

    @Override
    public void deleteById(Long aLong) {
        roundRepository.deleteById(aLong);
    }

    @Override
    public void setupRounds(LeagueDto leagueDto) {

        Comparator<TeamDto> teamComparator= new Comparator<TeamDto>() {
            @Override
            public int compare(TeamDto o1, TeamDto o2) {
                if(o1.getTournamentNumber()> o2.getTournamentNumber())
                    return 1;
                else
                    return 0;
            }
        };

        List<TeamDto> teamDtoList= new ArrayList<>(leagueDto.getTeams());
        teamDtoList.sort(teamComparator);

        TeamDto[] teamDtoArray= new TeamDto[teamDtoList.size()];
        int teamCounter= 0;
        for (int i = 0; i < teamDtoArray.length; i++) {
            teamDtoArray[teamCounter++]= teamDtoList.get(i);
        }

        int totalRounds= (teamDtoArray.length-1) * 2;

        for (int i = 0; i < totalRounds; i++) {

            RoundDto roundDto= new RoundDto();
            roundDto.setRoundNo(i+1);
            roundDto.setLeagueId(leagueDto.getId());

            for (int j = 0; j < teamDtoArray.length/2; j++) {
                TeamDto teamDtoA= teamDtoArray[j];
                TeamDto teamDtoB= teamDtoArray[teamDtoArray.length-1-j];

                MatchDto matchDto= new MatchDto();

                if(i < (totalRounds/2)){
                    halfLeagueSetup(teamDtoA, teamDtoB, matchDto);
                }
                else {
                    halfLeagueSetup(teamDtoB, teamDtoA, matchDto);
                }
                roundDto.getMatchDtoList().add(matchDto);

            }

            for (int j = teamDtoArray.length-1; j > 1 ; j--) {
                TeamDto pom= teamDtoArray[j];
                teamDtoArray[j]= teamDtoArray[j-1];
                teamDtoArray[j-1]= pom;
            }
            roundRepository.save(roundMapper.roundDtoToRound(roundDto));

        }

    }

    private static boolean isEven(TeamDto teamA, TeamDto teamB){
        return (teamA.getTournamentNumber() + teamB.getTournamentNumber()) % 2 == 0;
    }

    private static void halfLeagueSetup(TeamDto host, TeamDto guest, MatchDto matchDto){
        if(isEven(host, guest)){
            if(host.getTournamentNumber()> guest.getTournamentNumber()){
                matchDto.setHostId(host.getId());
                matchDto.setGuestId(guest.getId());
            }
            else {
                matchDto.setHostId(guest.getId());
                matchDto.setGuestId(host.getId());
            }
        }
        else {
            if(host.getTournamentNumber()< guest.getTournamentNumber()){
                matchDto.setHostId(host.getId());
                matchDto.setGuestId(guest.getId());
            }
            else {
                matchDto.setHostId(guest.getId());
                matchDto.setGuestId(host.getId());
            }
        }
    }
}
