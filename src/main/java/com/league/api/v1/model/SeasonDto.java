package com.league.api.v1.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
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

    @JsonSerialize(using = ToStringSerializer.class)
    private LocalDate startDate;

    @JsonSerialize(using = ToStringSerializer.class)
    private LocalDate endDate;
    private Set<LeagueDto> leagues= new HashSet<>();
}
