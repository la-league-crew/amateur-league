package com.league.service;

import com.league.api.v1.model.LeagueDto;
import com.league.api.v1.model.RoundDto;

public interface RoundService extends BaseService<RoundDto,Long>{

    void setupRounds(LeagueDto leagueDto);
}
