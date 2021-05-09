package com.league.service.impl;

import com.league.api.v1.mapper.SeasonMapper;
import com.league.api.v1.model.LeagueDto;
import com.league.api.v1.model.SeasonDto;
import com.league.domain.Season;
import com.league.repositories.SeasonRepository;
import com.league.service.SeasonService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SeasonServiceImpl implements SeasonService {

    private final SeasonRepository seasonRepository;
    private final SeasonMapper seasonMapper;

    public SeasonServiceImpl(SeasonRepository seasonRepository, SeasonMapper seasonMapper) {
        this.seasonRepository = seasonRepository;
        this.seasonMapper = seasonMapper;
    }

    @Override
    public Set<SeasonDto> findAll() {
        return seasonRepository.findAll()
                .stream()
                .map(seasonMapper::seasonToSeasonDto)
                .collect(Collectors.toSet());
    }

    @Override
    public SeasonDto save(SeasonDto seasonDto) {
        Season season= seasonMapper.seasonDtoToSeason(seasonDto);
        return seasonMapper.seasonToSeasonDto(seasonRepository.save(season));
    }

    @Override
    public SeasonDto update(Long aLong, SeasonDto seasonDto) {
        return seasonRepository.findById(aLong)
                .map(season -> {
                    if(seasonDto.getStartDate()!= null)
                        season.setStartDate(seasonDto.getStartDate());
                    if(seasonDto.getEndDate()!= null)
                        season.setEndDate(seasonDto.getEndDate());
                    Season savedSeason= seasonRepository.save(season);
                    return seasonMapper.seasonToSeasonDto(savedSeason);
                })
                .orElseThrow(RuntimeException:: new);
    }

    @Override
    public SeasonDto findById(Long aLong) {
        return seasonMapper.seasonToSeasonDto(seasonRepository.findById(aLong).orElse(null));
    }

    @Override
    public void deleteById(Long aLong) {
        seasonRepository.deleteById(aLong);
    }

    @Override
    public boolean addLeague(Long seasonId, LeagueDto leagueDto) {
        Optional<Season> seasonOptional= seasonRepository.findById(seasonId);

        if(seasonOptional.isPresent()){
            SeasonDto seasonDto= seasonMapper.seasonToSeasonDto(seasonOptional.get());
            if(seasonDto.getStartDate().compareTo(LocalDate.now())< 0)
                return false;
            seasonDto.getLeagues().add(leagueDto);
            seasonRepository.save(seasonMapper.seasonDtoToSeason(seasonDto));
            return true;
        }
        return false;
    }
}
