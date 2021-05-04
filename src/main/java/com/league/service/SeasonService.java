package com.league.service;

import com.league.api.v1.model.LeagueDto;
import com.league.api.v1.model.SeasonDto;

public interface SeasonService extends BaseService<SeasonDto, Long>{

    boolean addLeague(Long seasonId, LeagueDto leagueDto);
}
