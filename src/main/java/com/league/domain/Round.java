package com.league.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Round extends BaseEntity{

    private Integer roundNo;
    private League league;
    private List<Match> matches= new ArrayList<>();

}
