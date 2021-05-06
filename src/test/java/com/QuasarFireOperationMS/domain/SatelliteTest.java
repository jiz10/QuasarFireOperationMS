package com.QuasarFireOperationMS.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author jiz10
 * 6/5/21
 */

@ExtendWith(MockitoExtension.class)
@WebMvcTest(SatelliteTest.class)
public class SatelliteTest {

    private Satellite satellite;
    private static String[] message;
    private static String NAME = "Sato";

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
    public void validateCreation() {
        assertThat(satellite.getId()).isNotNull();
        assertThat(satellite.getDistance()).isEqualTo(100.0);
        assertThat(satellite.getMessage()).isEqualTo(message);
        assertThat(satellite.getName()).isEqualTo(NAME);
        assertThat(satellite.getCreatedDate()).isNotNull();

    }

    @Test
    public void validateCreationWithArgs() {

        Satellite satelliteInfo = new Satellite(UUID.randomUUID(), LocalDateTime.now(), NAME, 199.0, message);
        assertThat(satelliteInfo.getId()).isNotNull();
        assertThat(satelliteInfo.getDistance()).isNotNull();
        assertThat(satelliteInfo.getMessage()).isNotNull();
        assertThat(satelliteInfo.getName()).isNotNull();
        assertThat(satelliteInfo.getCreatedDate()).isNotNull();
    }
}
