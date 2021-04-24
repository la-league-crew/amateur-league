package com.league.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "match")
public class Match extends BaseEntity implements Serializable
{

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "round_id")
    private Round round;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "host_id")
    private Team host;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "guest_id")
    private Team guest;

    @Column(name = "host_score")
    private int hostScore;

    @Column(name = "guest_score")
    private int guestScore;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "match_date")
    private LocalDate matchDate;

}
