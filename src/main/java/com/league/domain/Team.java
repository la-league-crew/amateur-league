package com.league.domain;

public class Team extends BaseEntity
{
    private String teamTitle;

//    Important for Round matchmaking
    private int tournamentNumber;

    private League league;
    private TeamInfo teamInfo;

    public String getTeamTitle() {
        return teamTitle;
    }

    public void setTeamTitle(String teamTitle) {
        this.teamTitle = teamTitle;
    }

    public int getTournamentNumber() {
        return tournamentNumber;
    }

    public void setTournamentNumber(int tournamentNumber) {
        this.tournamentNumber = tournamentNumber;
    }

    public League getLeague() {
        return league;
    }

    public void setLeague(League league) {
        this.league = league;
    }

    public TeamInfo getTeamInfo() {
        return teamInfo;
    }

    public void setTeamInfo(TeamInfo teamInfo) {
        this.teamInfo = teamInfo;
    }
}
