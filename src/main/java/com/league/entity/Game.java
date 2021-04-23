package com.league.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "game_table")
public class Game {

	@Id
	@GeneratedValue
	private long id;
	
	private Date date;
	
	@OneToOne
	@JoinColumn(name = "home_club_id")
	private Club home;
	
	@Column(columnDefinition = "int default 0")
	private int homeGoals;
	
	@OneToOne
	@JoinColumn(name = "away_club_id")
	private Club away;
	
	@Column(columnDefinition = "int default 0")
	private int awayGoals;
	
	@ManyToOne
	@JoinColumn(name = "round_id")
	private Round round;
	
	public Game() {}

	public Game(Date date, Club home, Club away) {
		this.date = date;
		this.home = home;
		this.away = away;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Club getHome() {
		return home;
	}

	public void setHome(Club home) {
		this.home = home;
	}

	public Club getAway() {
		return away;
	}

	public void setAway(Club away) {
		this.away = away;
	}
}
