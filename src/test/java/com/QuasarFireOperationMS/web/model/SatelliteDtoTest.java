package com.QuasarFireOperationMS.web.model;

import com.QuasarFireOperationMS.domain.Satellite;
import com.QuasarFireOperationMS.domain.SatelliteTest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author jiz10
 * 6/5/21
 */

@ExtendWith(MockitoExtension.class)
@WebMvcTest(SatelliteTest.class)
public class SatelliteDtoTest {
    private Satellite satellite;
    private static String[] message;
    private static String NAME = "Sato";

    @Autowired
    ObjectMapper objectMapper;


    @BeforeEach
    public void setup() {
        message = new String[]{"un", "mensaje"};
        satellite = new Satellite();
        satellite.setCreatedDate(LocalDateTime.now());
        satellite.setDistance(100.0);
        satellite.setId(UUID.randomUUID());
        satellite.setMessage(message);
        satellite.setName("Sato");
    }

    @Test
    public void validateCreation() throws JsonProcessingException {

        String json = "{ \"name\":\"kenobi\", \"distance\":100.0, \"message\":[ \"\", \"este\", \"es\", \"un\", \"mensaje\" ] },";
        SatelliteDto satelliteDto = objectMapper.readValue(json, SatelliteDto.class);

        List<SatelliteDto> listSat = new ArrayList<>();
        listSat.add(satelliteDto);

        SatellitesDto satellitesDto = SatellitesDto.builder().satellites(listSat).build();

        assertThat(satellitesDto.getSatellites().size()).isEqualTo(1);
        assertThat(satellitesDto.toString()).isNotNull();

    }


}
