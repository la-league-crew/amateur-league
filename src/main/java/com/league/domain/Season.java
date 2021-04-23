package com.league.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class Season extends BaseEntity
{
    private LocalDate startDate;
    private LocalDate endDate;
    private Set<League> leagues= new HashSet<>();
}
