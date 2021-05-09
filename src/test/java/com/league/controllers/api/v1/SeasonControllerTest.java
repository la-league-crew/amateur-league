package com.league.controllers.api.v1;

import com.league.api.v1.model.SeasonDto;
import com.league.service.SeasonService;
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;

class SeasonControllerTest {

    SeasonController seasonController;

    MockMvc mockMvc;

    @Mock
    SeasonService seasonService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        seasonController= new SeasonController(seasonService);
        mockMvc= MockMvcBuilders.standaloneSetup(seasonController).build();
    }

    @Test
    void createSeason() throws Exception {
//        Given
        String uri= "/api/seasons/new";
        SeasonDto seasonDto= new SeasonDto();
        seasonDto.setId(1L);
        String inputJson= JsonConverter.mapToJson(seasonDto);

        Mockito.when(seasonController.createSeason(Mockito.any(SeasonDto.class))).thenReturn(seasonDto);

//        When
        MvcResult mvcResult= mockMvc.perform(MockMvcRequestBuilders.post(uri)
                    .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
                    .andReturn();

//        Then
        int status= mvcResult.getResponse().getStatus();
        Assertions.assertEquals(201, status);
        String content= mvcResult.getResponse().getContentAsString();
        Assertions.assertEquals(content, inputJson);

    }

    @Test
    void updateSeason() throws Exception {
//        Given
        String uri= "/api/seasons/1";
        SeasonDto seasonDto= new SeasonDto();
        seasonDto.setId(1L);
        seasonDto.setStartDate(LocalDate.of(2021,2,20));
        String inputJson= JsonConverter.mapToJson(seasonDto);

        Mockito.when(seasonService.update(Mockito.anyLong(), Mockito.any(SeasonDto.class))).thenReturn(seasonDto);

//        When
        MvcResult mockResult= mockMvc.perform(MockMvcRequestBuilders.patch(uri)
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(inputJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

//        Then
        int status= mockResult.getResponse().getStatus();
        Assertions.assertEquals(200, status);
        String content= mockResult.getResponse().getContentAsString();
        Assertions.assertEquals(content, inputJson);
    }

    @Test
    void findAll() {
    }

    @Test
    void findById() {
    }

    @Test
    void deleteById() {
    }
}