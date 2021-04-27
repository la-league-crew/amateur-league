package com.league.service.impl;

import com.league.api.v1.mapper.SeasonMapper;
import com.league.api.v1.model.SeasonDto;
import com.league.domain.League;
import com.league.domain.Season;
import com.league.repositories.SeasonRepository;
import com.league.service.SeasonService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

class SeasonServiceImplTest {

    SeasonService seasonService;

    SeasonMapper seasonMapper;

    @Mock
    SeasonRepository seasonRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        seasonMapper= SeasonMapper.INSTANCE;
        seasonService= new SeasonServiceImpl(seasonRepository,seasonMapper);
    }

    @Test
    void findAll() {
//        Given
        Season season1= new Season();
        season1.setId(1L);
        Season season2= new Season();
        season2.setId(4L);

        List<Season> seasonList= Arrays.asList(season1, season2);

        Mockito.when(seasonRepository.findAll()).thenReturn(seasonList);

//        When
        Set<SeasonDto> seasonDtoSet= seasonService.findAll();

//        Then
        Assertions.assertEquals(seasonDtoSet.size(),2);
    }

    @Test
    void testLeaguesInsideSeason(){
//        Given
        Season season= new Season();
        season.setId(1L);
        League league1= new League();
        league1.setId(11L);
        League league2= new League();
        league2.setId(15L);
        season.getLeagues().add(league1);
        season.getLeagues().add(league2);

        Mockito.when(seasonRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(season));

//        When
        SeasonDto seasonDto= seasonService.findById(1L);

//        Then
        Assertions.assertEquals(seasonDto.getLeagues().size(), 2);
        Assertions.assertEquals(seasonDto.getLeagues()
                .stream()
                .filter(leagueDto -> leagueDto.getId()== 11L)
                .findFirst()
                .get().getId(), 11);
    }

    @Test
    void save() {
//        Given
        Season season= new Season();
        season.setId(1L);

        Mockito.when(seasonRepository.save(Mockito.any(Season.class))).thenReturn(season);

//        When
        SeasonDto seasonDto= seasonService.save(seasonMapper.seasonToSeasonDto(season));

//        Then
        Assertions.assertNotNull(seasonDto);
        Assertions.assertEquals(seasonDto.getId(), 1);
    }

    @Test
    void testLeaguesAfterSavingSeason(){
//        Given
        Season season= new Season();
        season.setId(1L);
        League league1= new League();
        league1.setId(11L);
        League league2= new League();
        league2.setId(15L);
        season.getLeagues().add(league1);
        season.getLeagues().add(league2);

        Mockito.when(seasonRepository.save(Mockito.any(Season.class))).thenReturn(season);

//        When
        SeasonDto seasonDto= seasonService.save(seasonMapper.seasonToSeasonDto(season));

//        Then
        Assertions.assertEquals(seasonDto.getLeagues().size(), 2);
        Assertions.assertEquals(seasonDto.getLeagues()
                .stream()
                .filter(leagueDto -> leagueDto.getId()== 11)
                .findFirst()
                .get().getId(), 11);
    }

    @Test
    void findById() {
//        Given
        long seasonId= 2L;
        Season season= new Season();
        season.setId(seasonId);

        Mockito.when(seasonRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(season));

//        When
        SeasonDto seasonDto= seasonService.findById(seasonId);

//        Then
        Assertions.assertEquals(seasonDto.getId(), seasonId);
    }

    @Test
    void deleteById() {
//        Given
        long seasonId= 2L;

//        When
        seasonService.deleteById(seasonId);

//        Then
        Mockito.verify(seasonRepository, Mockito.times(1)).deleteById(Mockito.anyLong());
    }
}