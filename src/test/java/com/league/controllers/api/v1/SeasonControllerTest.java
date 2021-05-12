package com.league.controllers.api.v1;

import com.league.api.v1.model.SeasonDto;
import com.league.service.SeasonService;
import org.hamcrest.Matchers;
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
import java.util.HashSet;
import java.util.Set;

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
    void findAll() throws Exception {
//        Given
        String uri= "/api/seasons/";
        Set<SeasonDto> seasonDtoSet= new HashSet<>();
        seasonDtoSet.add(new SeasonDto());
        seasonDtoSet.add(new SeasonDto());
        String jsonInput= JsonConverter.mapToJson(seasonDtoSet);

        Mockito.when(seasonService.findAll()).thenReturn(seasonDtoSet);

//        When
        MvcResult result= mockMvc.perform(MockMvcRequestBuilders.get(uri)
                    .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

//        Then
        int status= result.getResponse().getStatus();
        Assertions.assertEquals(200, status);
        String content= result.getResponse().getContentAsString();
        Assertions.assertEquals(jsonInput, content);
    }

    @Test
    void findById() throws Exception {
//        Given
        String uri= "/api/seasons/1";
        SeasonDto seasonDto= new SeasonDto();
        seasonDto.setId(1L);
        LocalDate date= LocalDate.of(2021,04,29);
        seasonDto.setStartDate(date);
        String jsonInput= JsonConverter.mapToJson(seasonDto);

        Mockito.when(seasonService.findById(Mockito.anyLong())).thenReturn(seasonDto);

//        When
        MvcResult result= mockMvc.perform(MockMvcRequestBuilders.get(uri)
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.equalTo(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.startDate",Matchers.equalTo(date.toString())))
                .andReturn();

//        Then
        int status= result.getResponse().getStatus();
        Assertions.assertEquals(200, status);
        String content= result.getResponse().getContentAsString();
        Assertions.assertEquals(jsonInput, content);
    }

    @Test
    void deleteById() throws Exception {
//        Given
        String uri= "/api/seasons/1";

//        When
        MvcResult result= mockMvc.perform(MockMvcRequestBuilders.delete(uri)
                    .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

//        Then
        int status= result.getResponse().getStatus();
        Assertions.assertEquals(200, status);

        Mockito.verify(seasonService, Mockito.times(1)).deleteById(Mockito.anyLong());
    }
}