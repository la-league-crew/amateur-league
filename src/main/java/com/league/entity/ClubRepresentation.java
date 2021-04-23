package com.league.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "representation_table")
public class ClubRepresentation {

	@Id
	@GeneratedValue
	private long id;
	
	private long clubId;
	private String clubName;
	
	@Column(columnDefinition = "int default 0") 
	private int played;
	
	@Column(columnDefinition = "int default 0")
	private int won;
	
	@Column(columnDefinition = "int default 0")
	private int lost;
	
	@Column(columnDefinition = "int default 0")
	private int gf; //goals scored
	
	@Column(columnDefinition = "int default 0")
	private int ga; //goals conceded
	
	@Column(columnDefinition = "int default 0")
	private int gd; //goal difference
	
	@Column(columnDefinition = "int default 0")
	private int points;
	
	@ManyToOne
	@JoinColumn(name = "season_id")
	private Season season;
	
	public ClubRepresentation() {
		super();
	}

	public ClubRepresentation(long clubId, String clubName) {
		this.clubId = clubId;
		this.clubName = clubName;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getClubId() {
		return clubId;
	}

	public void setClubId(long clubId) {
		this.clubId = clubId;
	}

	public String getClubName() {
		return clubName;
	}

	public void setClubName(String clubName) {
		this.clubName = clubName;
	}

	public int getPlayed() {
		return played;
	}

	public void setPlayed(int played) {
		this.played = played;
	}

	public int getWon() {
		return won;
	}

	public void setWon(int won) {
		this.won = won;
	}

	public int getLost() {
		return lost;
	}

	public void setLost(int lost) {
		this.lost = lost;
	}

	public int getGf() {
		return gf;
	}

	public void setGf(int gf) {
		this.gf = gf;
	}

	public int getGa() {
		return ga;
	}

	public void setGa(int ga) {
		this.ga = ga;
	}

	public int getGd() {
		return gd;
	}

	public void setGd(int gd) {
		this.gd = gd;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public Season getSeason() {
		return season;
	}

	public void setSeason(Season season) {
		this.season = season;
	}	
}
