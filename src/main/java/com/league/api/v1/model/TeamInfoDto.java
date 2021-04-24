package com.league.api.v1.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TeamInfoDto {

    private Long id;
    private String country;
    private String city;
    private String stadiumName;
}
