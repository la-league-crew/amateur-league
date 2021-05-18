package com.league.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class DevicesAndLocations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private LeagueUser leagueUser;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<LeagueDevice> devices;

}
