package com.league.entity;

import java.util.Date;
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
@Table(name = "round_table")
public class Round {

	@Id
	@GeneratedValue
	private long id;
	
	private Date date;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "round")
	private List<Game> games;
	
	@ManyToOne
	@JoinColumn(name = "season_id")
	private Season season;
	
	public Round() {}

	public Round(Date date) {
		this.date = date;
	}

	public Round(Date date, List<Game> games) {
		this.date = date;
		this.games = games;
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

	public List<Game> getGames() {
		return games;
	}

	public void setGames(List<Game> games) {
		this.games = games;
	}

}
