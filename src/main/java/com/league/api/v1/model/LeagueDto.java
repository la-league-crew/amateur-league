package com.league.api.v1.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class LeagueDto
{
    private Long id;
    private String title;
    private String sport;
    private Long seasonId;
    private Set<TeamDto> teams= new HashSet<>();
    private Set<RoundDto> rounds= new HashSet<>();
}
