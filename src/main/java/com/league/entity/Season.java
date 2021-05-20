package com.league.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "season_table")
public class Season {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	//@Column(name = "league_name", length = 50) //nullable = false 
	private String leagueName;
	private long leagueIdentification;
	private String description;
	private boolean available;
	private boolean started;
	private boolean closed;
	private int activeRoundId;
	
	
//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "season")
//	private List<Club> clubs;
	
	@JsonManagedReference
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "season", cascade = CascadeType.ALL)
	private List<Round> rounds = new ArrayList<>();;

	@JsonManagedReference
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "season", cascade = CascadeType.ALL)
	private List<ClubRepresentation> representations = new ArrayList<>();;
	
	@JsonManagedReference
	@ManyToOne
	@JoinColumn(name = "league_id")
	private League league;

	public void addRound(Round round) {
		this.rounds.add(round);
		round.setSeason(this);
	}
	
	public void addRoundList(List<Round> rounds) {
		rounds.stream().forEach(this::addRound);
		//this.rounds.addAll(rounds);
	}
	
	public void addClubRepresentation(ClubRepresentation rep) {
		this.representations.add(rep);
		rep.setSeason(this);
	}
	
	public Round getActiveRound() {
		return rounds.get(activeRoundId);
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLeagueName() {
		return leagueName;
	}

	public void setLeagueName(String leagueName) {
		this.leagueName = leagueName;
	}

	public long getLeagueIdentification() {
		return leagueIdentification;
	}

	public void setLeagueIdentification(long leagueIdentification) {
		this.leagueIdentification = leagueIdentification;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public boolean isClosed() {
		return closed;
	}

	public void setClosed(boolean closed) {
		this.closed = closed;
	}

	
	
//	public List<Club> getClubs() {
//		return clubs;
//	}
//
//	public void setClubs(List<Club> clubs) {
//		this.clubs = clubs;
//	}

	public int getActiveRoundId() {
		return activeRoundId;
	}

	public void setActiveRoundId(int activeRoundId) {
		this.activeRoundId = activeRoundId;
	}

	public List<Round> getRounds() {
		return rounds;
	}

	public void setRounds(List<Round> rounds) {
		this.rounds = rounds;
	}

	public List<ClubRepresentation> getRepresentations() {
		return representations;
	}

	public void setRepresentations(List<ClubRepresentation> representations) {
		this.representations = representations;
	}

	public League getLeague() {
		return league;
	}

	public void setLeague(League league) {
		this.league = league;
	}

	public boolean isStarted() {
		return started;
	}

	public void setStarted(boolean started) {
		this.started = started;
	}
}
