package com.league.service.impl;

import com.league.api.v1.mapper.SeasonMapper;
import com.league.api.v1.model.SeasonDto;
import com.league.domain.Season;
import com.league.repositories.SeasonRepository;
import com.league.service.SeasonService;
import org.springframework.stereotype.Service;

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
    public SeasonDto findById(Long aLong) {
        return seasonMapper.seasonToSeasonDto(seasonRepository.findById(aLong).orElse(null));
    }

    @Override
    public void deleteById(Long aLong) {
        seasonRepository.deleteById(aLong);
    }
}
