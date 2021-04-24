package com.league.api.v1.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class MatchDto {

    private Long id;
    private Long roundId;
    private Long hostId;
    private Long guestId;
    private int hostScore;
    private int guestScore;
    private LocalDate matchDate;
}
