package com.league.api.v1.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class SeasonDto {

    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private Set<LeagueDto> leagues= new HashSet<>();
}
