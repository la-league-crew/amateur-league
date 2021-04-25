package com.league.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "season_table")
public class Season {

	@Id
	@GeneratedValue
	private long id;
	
	private String name;
	private String leagueName;
	private long leagueIdentification;
	private String description;
	private boolean available;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "season")
	private List<Club> clubs;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "season")
	private List<Round> rounds;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "season")
	private List<ClubRepresentation> representations;
	
	@ManyToOne
	@JoinColumn(name = "league_id")
	private League league;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public List<Club> getClubs() {
		return clubs;
	}

	public void setClubs(List<Club> clubs) {
		this.clubs = clubs;
	}

	public List<Round> getRounds() {
		return rounds;
	}

	public void setRounds(List<Round> rounds) {
		this.rounds = rounds;
	}
	
	
}
