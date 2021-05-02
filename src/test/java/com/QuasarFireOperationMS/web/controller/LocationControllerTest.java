package com.QuasarFireOperationMS.web.controller;

import com.QuasarFireOperationMS.service.LocationService;
import com.QuasarFireOperationMS.web.model.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
@WebMvcTest(LocationController.class)
@ComponentScan(basePackages = "exercisesms.com.example.ms01.web.mappers")
class LocationControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    LocationService locationService;

    SatellitesDto satellitesDto;
    SingleSatelliteDto singleSatelliteDto;

    @BeforeEach
    public void setUp() {

        SatellitesDto satellitesDto = new SatellitesDto();
        SingleSatelliteDto singleSatelliteDto = new SingleSatelliteDto();
    }

    @Test
    void getLocationFromSatelliteGroup() throws Exception {

        String json = "{ \"satellites\":[ { \"name\":\"kenobi\", \"distance\":100.0, \"message\":[ \"este\", \"\", \"\", \"mensaje\", \"\" ] }, { \"name\":\"sato\", \"distance\":142.7, \"message\":[ \"este\", \"\", \"un\", \"\", \"\" ] }, { \"name\":\"skywalker\", \"distance\":115.5, \"message\":[ \"\", \"es\", \"\", \"\", \"secreto\" ] } ] }";
        SatellitesDto satellitesDto = objectMapper.readValue(json, SatellitesDto.class);

        PositionDto positionDto = PositionDto.builder()
                .x(100.0)
                .y(100.0)
                .build();

        given(locationService.getLocationFromSatellitesGroup(satellitesDto)).willReturn(LocationInfoDto.builder()
                .message("Test message")
                .position(positionDto)
                .build());

        mockMvc.perform(post("/api/v1/topsecret/")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(satellitesDto)))
                .andExpect(status().isOk());

    }

    @Test
    void postSatelliteSplit() throws Exception {

        String json = "{ \"distance\": 100, \"message\": [\"este\", \"\", \"\", \"mensaje\", \"\"] }";
        SingleSatelliteDto singleSatelliteDto = objectMapper.readValue(json, SingleSatelliteDto.class);

        given(locationService.saveSatelliteInfo(singleSatelliteDto, "sato")).willReturn(SingleSatelliteInfoDto.builder()
                .name("sato")
                .distance(100.0)
                .build());

        mockMvc.perform(post("/api/v1/topsecret_split/sato")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(singleSatelliteDto)))
                .andExpect(status().isCreated());

    }
    @Test
    void postSatelliteSplitWrongSatName() throws Exception {

        String json = "{ \"distance\": 100, \"message\": [\"este\", \"\", \"\", \"mensaje\", \"\"] }";
        SingleSatelliteDto singleSatelliteDto = objectMapper.readValue(json, SingleSatelliteDto.class);
        mockMvc.perform(post("/api/v1/topsecret_split/sato")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(singleSatelliteDto)))
                .andExpect(status().isCreated());

    }

    @Test
    void getLocationSplit() throws Exception {

        given(locationService.getLocationSplit()).willReturn(LocationInfoDto.builder().position(PositionDto.builder()
                .x(100.0)
                .y(100.0)
                .build())
                .message("Test message")
                .build());

        mockMvc.perform(get("/api/v1/topsecret_split/")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }
}