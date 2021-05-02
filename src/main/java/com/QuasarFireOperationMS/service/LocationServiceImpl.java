package com.QuasarFireOperationMS.service;

import com.QuasarFireOperationMS.domain.Satellite;
import com.QuasarFireOperationMS.repositories.SatelliteRepository;
import com.QuasarFireOperationMS.util.LocationCalculator;
import com.QuasarFireOperationMS.web.error.InsufficientInformationException;
import com.QuasarFireOperationMS.web.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author jiz10
 * 30/4/21
 */
@Service
@Slf4j
public class LocationServiceImpl implements LocationService {

    @Autowired
    LocationCalculator locationCalculator;

    @Autowired
    SatelliteRepository satelliteRepository;

    @Value("${satellites.names}")
    private String[] satellitesNames;


    @Override
    public LocationInfoDto getLocationFromSatellitesGroup(SatellitesDto satellitesDto) {

        double[] location = locationCalculator.getLocation(getDistances(satellitesDto));
        PositionDto positionDto = PositionDto.builder()
                .x(location[0])
                .y(location[1])
                .build();
        return LocationInfoDto.builder()
                .message("Mensaje de prueba")
                .position(positionDto)
                .build();

    }

    @Override
    public SingleSatelliteInfoDto saveSatelliteInfo(SingleSatelliteDto singleSatelliteDto, String satellite_name) {

        Optional<Satellite> satelliteOptional = satelliteRepository.findByName(satellite_name);
        if (satelliteOptional.isPresent()) {
            log.info("Satellite info already exist, replacing info...");
            satelliteRepository.delete(satelliteOptional.get());
        }

        String[] messageArray = new String[singleSatelliteDto.getMessage().size()];
        messageArray = singleSatelliteDto.getMessage().toArray(messageArray);

        Satellite satelliteToSave = Satellite.builder()
                .createdDate(LocalDateTime.now())
                .name(satellite_name)
                .distance(singleSatelliteDto.getDistance())
                .message(messageArray)
                .build();

        Satellite satelliteSaved = satelliteRepository.save(satelliteToSave);
        log.info("Satellite info save: \n" + satelliteSaved);
        return SingleSatelliteInfoDto.builder()
                .name(satelliteSaved.getName())
                .distance(satelliteSaved.getDistance())
                .message(Arrays.asList(satelliteSaved.getMessage()))
                .build();
    }

    @Override
    public LocationInfoDto getLocationSplit() {

        List<Satellite> satelliteList = satelliteRepository.findAll();

        if (satelliteList.size() != 3)
            throw new InsufficientInformationException();

        double[] location = locationCalculator.getLocation(getDistances(satelliteList));
        PositionDto positionDto = PositionDto.builder()
                .x(location[0])
                .y(location[1])
                .build();

        return LocationInfoDto.builder()
                .message("Mensaje de prueba")
                .position(positionDto)
                .build();
    }

    private double[] getDistances(List<Satellite> satelliteList) {

        double distanceOne = 0;
        double distanceTwo = 0;
        double distanceThree = 0;
        List<String> satellitesNamesList = Arrays.asList(satellitesNames);
        log.info("Satellites names: " + satellitesNamesList);

        for (Satellite sat : satelliteList) {
            log.info("Satellite name: " + sat.getName());
            if (sat.getName().equalsIgnoreCase(satellitesNamesList.get(0))) {
                distanceOne = sat.getDistance();
            } else if (sat.getName().equalsIgnoreCase(satellitesNamesList.get(1))) {
                distanceTwo = sat.getDistance();
            } else if (sat.getName().equalsIgnoreCase(satellitesNamesList.get(2))) {
                distanceThree = sat.getDistance();
            }
        }

        return new double[]{distanceOne, distanceTwo, distanceThree};

    }

    private double[] getDistances(SatellitesDto satellitesDto) {

        double distanceOne = 0;
        double distanceTwo = 0;
        double distanceThree = 0;
        List<String> satellitesNamesList = Arrays.asList(satellitesNames);
        log.info("Satellites names: " + satellitesNamesList);

        for (SatelliteDto sat : satellitesDto.getSatellites()) {
            log.info("Satellite name: " + sat.getName());
            if (sat.getName().equalsIgnoreCase(satellitesNamesList.get(0))) {
                distanceOne = sat.getDistance();
            } else if (sat.getName().equalsIgnoreCase(satellitesNamesList.get(1))) {
                distanceTwo = sat.getDistance();
            } else if (sat.getName().equalsIgnoreCase(satellitesNamesList.get(2))) {
                distanceThree = sat.getDistance();
            }
        }

        return new double[]{distanceOne, distanceTwo, distanceThree};

    }
}
