package com.league.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TeamInfo extends BaseEntity
{
    private String country;
    private String city;
    private String stadiumName;
}
