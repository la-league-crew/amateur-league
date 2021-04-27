package com.league.service.impl;

import com.league.api.v1.mapper.RoundMapper;
import com.league.api.v1.model.RoundDto;
import com.league.domain.Round;
import com.league.repositories.RoundRepository;
import com.league.service.RoundService;
import org.springframework.stereotype.Service;

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
    public RoundDto findById(Long aLong) {
        return roundMapper.roundToRoundDto(roundRepository.findById(aLong).orElse(null));
    }

    @Override
    public void deleteById(Long aLong) {
        roundRepository.deleteById(aLong);
    }
}
