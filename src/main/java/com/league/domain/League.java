package com.league.domain;

import java.util.HashSet;
import java.util.Set;

public class League extends BaseEntity{

    private String title;

//    Optional for future implementation
    private String sport;

    private Set<Team> teams= new HashSet<>();

    private Set<Round> rounds= new HashSet<>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public Set<Team> getTeams() {
        return teams;
    }

    public void setTeams(Set<Team> teams) {
        this.teams = teams;
    }

    public Set<Round> getRounds() {
        return rounds;
    }

    public void setRounds(Set<Round> rounds) {
        this.rounds = rounds;
    }
}
