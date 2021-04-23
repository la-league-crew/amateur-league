package com.league.domain;

import java.util.ArrayList;
import java.util.List;

public class Round extends BaseEntity{

    private Integer roundNo;
    private League league;
    private List<Match> matches= new ArrayList<>();

    public Integer getRoundNo() {
        return roundNo;
    }

    public void setRoundNo(Integer roundNo) {
        this.roundNo = roundNo;
    }

    public League getLeague() {
        return league;
    }

    public void setLeague(League league) {
        this.league = league;
    }

    public List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }
}
