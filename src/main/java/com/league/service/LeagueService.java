package com.league.service;

import com.league.api.v1.model.LeagueDto;
import com.league.api.v1.model.TeamDto;

public interface LeagueService extends BaseService<LeagueDto, Long> {

    boolean addTeam(Long leagueId, TeamDto teamDto);

    boolean setTournamentDraw(Long leagueId);
}
