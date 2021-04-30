package com.QuasarFireOperationMS.service;

import com.QuasarFireOperationMS.web.model.LocationInfoDto;
import com.QuasarFireOperationMS.web.model.PositionDto;
import com.QuasarFireOperationMS.web.model.SatellitesDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author jiz10
 * 30/4/21
 */
@Service
@Slf4j
public class LocationServiceImpl implements LocationService {

    @Override
    public LocationInfoDto calculateLocationFromSatellitesGroup(SatellitesDto satellitesDto) {

        return LocationInfoDto.builder().message("Mensaje de prueba").position(PositionDto.builder().x(100.0).y(100.0).build()).build();

    }
}
