package com.league.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "game_table")
public class Game {

	@Id
	@GeneratedValue
	private Long id;
	
	private Long seasonId;
	private Date date;
	private Long homeId;
	private String homeName;
	private int homeGoals;
	private Long awayId;
	private String awayName;
	private int awayGoals;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "round_id")
	private Round round;
	
	public Game() {}
	
	public Game(Long homeId, String homeName, Long awayId, String awayName) {
		this.homeId = homeId;
		this.homeName = homeName;
		this.awayId = awayId;
		this.awayName = awayName;
	}
	
	@Override
	public String toString() {
		return "Game [homeId=" + homeId + ", homeName=" + homeName + "  VS  awayId=" + awayId + ", awayName=" + awayName
				+ "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((awayId == null) ? 0 : awayId.hashCode());
		result = prime * result + ((awayName == null) ? 0 : awayName.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((homeId == null) ? 0 : homeId.hashCode());
		result = prime * result + ((homeName == null) ? 0 : homeName.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	//overrides equals method to compare just ids and names of the teams
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Game) || (obj == null))
			return false;
		Game other = (Game) obj;
		if (awayId == null) {
			if (other.awayId != null)
				return false;
		} else if (!awayId.equals(other.awayId))
			return false;
		if (awayName == null) {
			if (other.awayName != null)
				return false;
		} else if (!awayName.equals(other.awayName))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (homeId == null) {
			if (other.homeId != null)
				return false;
		} else if (!homeId.equals(other.homeId))
			return false;
		if (homeName == null) {
			if (other.homeName != null)
				return false;
		} else if (!homeName.equals(other.homeName))
			return false;
		if (id != other.id)
			return false;
		return true;
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

	public Long getHomeId() {
		return homeId;
	}

	public void setHomeId(Long homeId) {
		this.homeId = homeId;
	}

	public String getHomeName() {
		return homeName;
	}

	public void setHomeName(String homeName) {
		this.homeName = homeName;
	}

	public Long getAwayId() {
		return awayId;
	}

	public void setAwayId(Long awayId) {
		this.awayId = awayId;
	}

	public String getAwayName() {
		return awayName;
	}

	public void setAwayName(String awayName) {
		this.awayName = awayName;
	}

	public Round getRound() {
		return round;
	}

	public void setRound(Round round) {
		this.round = round;
	}

	public int getHomeGoals() {
		return homeGoals;
	}

	public void setHomeGoals(int homeGoals) {
		this.homeGoals = homeGoals;
	}

	public int getAwayGoals() {
		return awayGoals;
	}

	public void setAwayGoals(int awayGoals) {
		this.awayGoals = awayGoals;
	}

	public Long getSeasonId() {
		return seasonId;
	}

	public void setSeasonId(Long seasonId) {
		this.seasonId = seasonId;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	
	
	
//	@OneToOne
//	@JoinColumn(name = "home_club_id")
//	private Club home;
//	
//	@Column(columnDefinition = "int default 0")
//	private int homeGoals;
//	
//	@OneToOne
//	@JoinColumn(name = "away_club_id")
//	private Club away;
//	
//	@Column(columnDefinition = "int default 0")
//	private int awayGoals;
//	
//	@ManyToOne
//	@JoinColumn(name = "round_id")
//	private Round round;
//	
//	public Game() {}
//
//	public Game(Date date, Club home, Club away) {
//		this.date = date;
//		this.home = home;
//		this.away = away;
//	}

	
}
