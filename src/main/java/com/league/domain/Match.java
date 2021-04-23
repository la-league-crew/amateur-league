package com.league.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class Match extends BaseEntity
{
    private Round round;
    private Team host;
    private Team guest;
    private int hostScore;
    private int guestScore;
    private LocalDate matchDate;

}
