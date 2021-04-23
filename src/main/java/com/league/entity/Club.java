package com.league.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "club_table")
public class Club {

	@Id
	@GeneratedValue
	private long id;
	
	private String name;
	private String description;
	private boolean available;
	
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
	@JoinColumn(name = "round_id")
	@JsonIgnore
	private Round round;
	
	@ManyToOne
	@JoinColumn(name = "season_id")
	private Season season;
	
	public Club() {
		super();
	}

	public Club(String name, String description) {
		this.name = name;
		this.description = description;
	}

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

	public Round getRound() {
		return round;
	}

	public void setRound(Round round) {
		this.round = round;
	}	
}
