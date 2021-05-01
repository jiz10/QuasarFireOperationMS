package com.QuasarFireOperationMS.web.controller;

import com.QuasarFireOperationMS.service.LocationService;
import com.QuasarFireOperationMS.web.error.NoSatelliteFoundException;
import com.QuasarFireOperationMS.web.model.LocationInfoDto;
import com.QuasarFireOperationMS.web.model.SatellitesDto;
import com.QuasarFireOperationMS.web.model.SingleSatelliteDto;
import com.QuasarFireOperationMS.web.model.SingleSatelliteInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Arrays;

/**
 * @author jiz10
 * 30/4/21
 */
@Validated
@RequestMapping("api/v1")
@RestController
public class LocationController {

    @Autowired
    private LocationService locationService;

    @Value("${satellites.names}")
    private String[] satellitesNames;

    @PostMapping(path = "/topsecret/")
    public ResponseEntity<LocationInfoDto> handlePost(@Valid @NotNull @RequestBody SatellitesDto satellitesDto) {

        LocationInfoDto locationInfoDto = locationService.calculateLocationFromSatellitesGroup(satellitesDto);
        return new ResponseEntity<LocationInfoDto>(locationInfoDto, HttpStatus.OK);
    }

    @PostMapping(path = "/topsecret_split/{satellite_name}")
    public ResponseEntity handlePost(@PathVariable @NotBlank String satellite_name, @Valid @NotNull @RequestBody SingleSatelliteDto singleSatelliteDto) {

        if (!Arrays.asList(satellitesNames).contains(satellite_name.toUpperCase())) {
            throw new NoSatelliteFoundException();
        }

        SingleSatelliteInfoDto satelliteInfo = locationService.saveSatelliteInfo(singleSatelliteDto, satellite_name);
        return new ResponseEntity<SingleSatelliteInfoDto>(satelliteInfo, HttpStatus.CREATED);

    }
}
