package com.league.api.v1.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class RoundDto {

    private Long id;
    private int roundNo;
    private Long leagueId;
    private Set<MatchDto> matchDtoList= new HashSet<>();
}
