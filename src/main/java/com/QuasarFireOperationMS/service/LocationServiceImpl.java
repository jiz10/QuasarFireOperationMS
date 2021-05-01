package com.QuasarFireOperationMS.service;

import com.QuasarFireOperationMS.util.LocationCalculator;
import com.QuasarFireOperationMS.web.model.LocationInfoDto;
import com.QuasarFireOperationMS.web.model.PositionDto;
import com.QuasarFireOperationMS.web.model.SatelliteDto;
import com.QuasarFireOperationMS.web.model.SatellitesDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @author jiz10
 * 30/4/21
 */
@Service
@Slf4j
public class LocationServiceImpl implements LocationService {

    @Autowired
    LocationCalculator locationCalculator;

    @Value("${satellites.names}")
    private String[] satellitesNames;

    @Override
    public LocationInfoDto calculateLocationFromSatellitesGroup(SatellitesDto satellitesDto) {

        double distanceOne = 0;
        double distanceTwo = 0;
        double distanceThree = 0;

        List<String> satellitesNamesList = Arrays.asList(satellitesNames);
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
        double[] distances = new double[]{distanceOne, distanceTwo, distanceThree};
        double[] location = locationCalculator.getLocation(distances);
        PositionDto positionDto = PositionDto.builder().x(location[0]).y(location[1]).build();


        return LocationInfoDto.builder().message("Mensaje de prueba").position(positionDto).build();

    }
}
