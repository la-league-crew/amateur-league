package com.league.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Team extends BaseEntity
{
    private String teamTitle;

//    Important for Round matchmaking
    private int tournamentNumber;

    private League league;
    private TeamInfo teamInfo;

}
