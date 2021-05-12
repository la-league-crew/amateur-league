package com.league.controllers.api.v1;

import com.league.api.v1.model.LeagueDto;
import com.league.service.LeagueService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

class LeagueControllerTest {

    LeagueController controller;

    MockMvc mockMvc;

    @Mock
    LeagueService leagueService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        controller= new LeagueController(leagueService);
        mockMvc= MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void createLeague() throws Exception {
//        Given
        String uri= "/api/leagues/new";
        LeagueDto leagueDto= new LeagueDto();
        leagueDto.setId(1L);
        leagueDto.setTitle("Champion League");
        String inputJason= JsonConverter.mapToJson(leagueDto);

        Mockito.when(leagueService.save(Mockito.any(LeagueDto.class))).thenReturn(leagueDto);

//        When
        MvcResult result= mockMvc.perform(MockMvcRequestBuilders.post(uri)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(inputJason))
                .andReturn();

//        Then
        int status= result.getResponse().getStatus();
        String content= result.getResponse().getContentAsString();
        Assertions.assertEquals(201, status);
        Assertions.assertEquals(inputJason, content);
    }

    @Test
    void updateLeague() throws Exception {
//        Given
        String uri= "/api/leagues/1";
        LeagueDto leagueDto= new LeagueDto();
        leagueDto.setId(1L);
        leagueDto.setTitle("Champion League");
        String inputJson= JsonConverter.mapToJson(leagueDto);

        Mockito.when(leagueService.update(Mockito.anyLong(), Mockito.any(LeagueDto.class))).thenReturn(leagueDto);

//        When
        MvcResult result= mockMvc.perform(MockMvcRequestBuilders.patch(uri)
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(inputJson))
                .andReturn();

//        Then
        int status= result.getResponse().getStatus();
        Assertions.assertEquals(200, status);
        String content= result.getResponse().getContentAsString();
        Assertions.assertEquals(inputJson, content);
    }

    @Test
    void findAll() throws Exception {
//        Given
        String uri= "/api/leagues/";
        Set<LeagueDto> leagueDtoSet= new HashSet<>();
        leagueDtoSet.add(new LeagueDto());
        leagueDtoSet.add(new LeagueDto());

        String inputJson= JsonConverter.mapToJson(leagueDtoSet);

        Mockito.when(leagueService.findAll()).thenReturn(leagueDtoSet);

//        When
        MvcResult result= mockMvc.perform(MockMvcRequestBuilders.get(uri)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(inputJson))
                .andReturn();

//        Then
        int status= result.getResponse().getStatus();
        String content= result.getResponse().getContentAsString();
        Assertions.assertEquals(200, status);
        Assertions.assertEquals(inputJson, content);
    }

    @Test
    void findByID() throws Exception {
//        Given
        String uri= "/api/leagues/1";
        LeagueDto leagueDto= new LeagueDto();
        leagueDto.setId(1L);
        String inputJson= JsonConverter.mapToJson(leagueDto);

        Mockito.when(leagueService.findById(Mockito.anyLong())).thenReturn(leagueDto);

//        When
        MvcResult result= mockMvc.perform(MockMvcRequestBuilders.get(uri)
                    .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

//        Then
        int status= result.getResponse().getStatus();
        String content= result.getResponse().getContentAsString();
        Assertions.assertEquals(200, status);
        Assertions.assertEquals(inputJson, content);
    }

    @Test
    void deleteById() throws Exception {
//        Given
        String uri= "/api/leagues/1";

//        When
        MvcResult result= mockMvc.perform(MockMvcRequestBuilders.delete(uri)
                    .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

//        Then
        int status= result.getResponse().getStatus();
        Assertions.assertEquals(200, status);
        Mockito.verify(leagueService, Mockito.times(1)).deleteById(Mockito.anyLong());
    }
}