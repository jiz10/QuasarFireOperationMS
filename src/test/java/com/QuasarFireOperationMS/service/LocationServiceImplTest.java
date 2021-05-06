package com.QuasarFireOperationMS.service;

import com.QuasarFireOperationMS.domain.Satellite;
import com.QuasarFireOperationMS.repositories.SatelliteRepository;
import com.QuasarFireOperationMS.util.LocationCalculator;
import com.QuasarFireOperationMS.util.MessageCalculator;
import com.QuasarFireOperationMS.web.model.LocationInfoDto;
import com.QuasarFireOperationMS.web.model.SatellitesDto;
import com.QuasarFireOperationMS.web.model.SingleSatelliteDto;
import com.QuasarFireOperationMS.web.model.SingleSatelliteInfoDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;

/**
 * @author jiz10
 * 5/5/21
 */

@ExtendWith(MockitoExtension.class)
@WebMvcTest(LocationServiceImpl.class)
public class LocationServiceImplTest {


    @InjectMocks
    @Autowired
    LocationServiceImpl service;

    @MockBean
    SatelliteRepository satelliteRepository;

    @SpyBean
    LocationCalculator locationCalculator;

    @SpyBean
    MessageCalculator messageCalculator;

    @Autowired
    ObjectMapper objectMapper;

    SingleSatelliteDto singleSatelliteDto;
    LocationInfoDto locationInfoDto;
    double[] location;


    @BeforeEach
    public void setup() {
        double[] distances = {100.0, 142.7, 115.5};
        //location = new double[]{100.0, 100.0};
        //Mockito.lenient().when(locationCalculator.getLocation(distances)).thenReturn(location);
        double[] location = {100.0, 100.0};

    }


    @Test
    public void getLocationFromSatellitesGroup() throws JsonProcessingException {

        //location = new double[]{100.0, 100.0};
        double[] distances = {100.0, 142.7, 115.5};
        double[] location = {100.0, 100.0};
        String json = "{ \"satellites\":[ { \"name\":\"kenobi\", \"distance\":100.0, \"message\":[ \"este\", \"\", \"\", \"mensaje\", \"\" ] }, { \"name\":\"sato\", \"distance\":142.7, \"message\":[ \"este\", \"\", \"un\", \"\", \"\" ] }, { \"name\":\"skywalker\", \"distance\":115.5, \"message\":[ \"\", \"es\", \"\", \"\", \"secreto\" ] } ] }";
        SatellitesDto satellitesDto = objectMapper.readValue(json, SatellitesDto.class);

        //when(locationCalculator.getLocation(distances)).thenReturn(location);

        LocationInfoDto locationInfoDto = null;


        given(locationCalculator.getLocation(distances)).willReturn(location);
        given(messageCalculator.getMessage(anyList(), anyList(), anyList())).willReturn("Mensaje");


        try {
            locationInfoDto = service.getLocationFromSatellitesGroup(satellitesDto);
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertThat(locationInfoDto.getPosition()).isNotNull();
        assertThat(locationInfoDto.getMessage()).isNotNull();

    }

    @Test
    public void saveSatelliteInfo() throws JsonProcessingException {
        String json = "{ \"distance\": 142.7, \"message\":[\"\", \"\", \"es\", \"\", \"mensaje\"] }";
        singleSatelliteDto = objectMapper.readValue(json, SingleSatelliteDto.class);
        String satellite_name = "Sato";
        String[] message = {"un", "mensaje"};
        Satellite sat = Satellite.builder().name(satellite_name).distance(100.0).message(message).build();
        Optional<Satellite> optional = Optional.of(sat);

        SingleSatelliteInfoDto singleSatelliteInfoDtoResponse = null;

        given(satelliteRepository.findByName(anyString())).willReturn(optional);
        given(satelliteRepository.save(any())).willReturn(sat);

        try {
            singleSatelliteInfoDtoResponse = service.saveSatelliteInfo(singleSatelliteDto, satellite_name);
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertThat(singleSatelliteInfoDtoResponse.getName()).isNotNull();

    }

    @Test
    public void getLocationSplit() {

        double[] distances = {100.0, 150.0, 175.0};
        double[] location = {100.0,150.0};
        String[] message = {"un", "mensaje"};
        List<Satellite> satList = new ArrayList<>();
        Satellite sat1 = Satellite.builder().name("Sato").distance(100.0).message(message).build();
        Satellite sat2 = Satellite.builder().name("Skywalker").distance(100.0).message(message).build();
        Satellite sat3 = Satellite.builder().name("Kenobi").distance(100.0).message(message).build();

        satList.add(sat1);
        satList.add(sat2);
        satList.add(sat3);

        given(satelliteRepository.findAll()).willReturn(satList);
        given(locationCalculator.getLocation(distances)).willReturn(location);
        given(messageCalculator.getMessage(anyList(),anyList(),anyList())).willReturn("Este es un mensaje");

        try {
            locationInfoDto = service.getLocationSplit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertThat(locationInfoDto.getPosition()).isNotNull();
    }
}
