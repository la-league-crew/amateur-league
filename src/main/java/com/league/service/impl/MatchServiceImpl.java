package com.league.service.impl;

import com.league.api.v1.mapper.MatchMapper;
import com.league.api.v1.model.MatchDto;
import com.league.domain.Match;
import com.league.repositories.MatchRepository;
import com.league.service.MatchService;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MatchServiceImpl implements MatchService {

    private final MatchRepository matchRepository;
    private final MatchMapper matchMapper;

    public MatchServiceImpl(MatchRepository matchRepository, MatchMapper matchMapper) {
        this.matchRepository = matchRepository;
        this.matchMapper = matchMapper;
    }

    @Override
    public Set<MatchDto> findAll() {
        return matchRepository.findAll()
                .stream()
                .map(matchMapper::matchToMatchDto)
                .collect(Collectors.toSet());
    }

    @Override
    public MatchDto save(MatchDto matchDto) {
        Match match= matchMapper.matchDtoToMatch(matchDto);
        return matchMapper.matchToMatchDto(matchRepository.save(match));
    }

    @Override
    public MatchDto findById(Long aLong) {
        return matchMapper.matchToMatchDto(matchRepository.findById(aLong).orElse(null));
    }

    @Override
    public void deleteById(Long aLong) {
        matchRepository.deleteById(aLong);
    }
}
