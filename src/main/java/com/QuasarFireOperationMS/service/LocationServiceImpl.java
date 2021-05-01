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
            System.out.println(sat.getName());
            if (sat.getName().equalsIgnoreCase(satellitesNamesList.get(0))) {
                distanceOne = sat.getDistance();
                System.out.println(distanceOne);
            } else if (sat.getName().equalsIgnoreCase(satellitesNamesList.get(1))) {
                distanceTwo = sat.getDistance();
                System.out.println(distanceTwo);
            } else if (sat.getName().equalsIgnoreCase(satellitesNamesList.get(2))) {
                distanceThree = sat.getDistance();
                System.out.println(distanceThree);
            }
        }
        double[] distances = new double[]{distanceOne, distanceTwo, distanceThree};
        PositionDto positionDto = locationCalculator.getLocation(distances);
        return LocationInfoDto.builder().message("Mensaje de prueba").position(positionDto).build();

    }
}
