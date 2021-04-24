package com.league.api.v1.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TeamDto {

    private Long id;
    private String teamTitle;
    private int tournamentNumber;
    private Long leagueId;
    private Long teamInfoId;
}
