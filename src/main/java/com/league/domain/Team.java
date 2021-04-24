package com.league.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "team")
public class Team extends BaseEntity implements Serializable
{

    @Column(name = "team_title")
    private String teamTitle;

//    Important for Round matchmaking
    @Column(name = "tournament_umber")
    private int tournamentNumber;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "league_id")
    private League league;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "team_info_id")
    private TeamInfo teamInfo;

}
