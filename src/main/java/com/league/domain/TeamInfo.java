package com.league.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "team_info")
public class TeamInfo extends BaseEntity implements Serializable
{

    @Column(name = "country")
    private String country;

    @Column(name = "city")
    private String city;

    @Column(name = "stadium_name")
    private String stadiumName;
}
