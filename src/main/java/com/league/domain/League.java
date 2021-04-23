package com.league.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class League extends BaseEntity{

    private String title;

//    Optional for future implementation
    private String sport;

    private Set<Team> teams= new HashSet<>();

    private Set<Round> rounds= new HashSet<>();

}
