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
@Table(name = "league_table")
public class League {

	@Id
	@GeneratedValue
	private long id;
	
	private String name;
	private String description;
	private boolean available;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "league")
	private List<Season> seasons;
	
	public League() {}

	public League(long id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.available = true;
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

	public List<Season> getSeasons() {
		return seasons;
	}

	public void setSeasons(List<Season> seasons) {
		this.seasons = seasons;
	}	
	
	
}
