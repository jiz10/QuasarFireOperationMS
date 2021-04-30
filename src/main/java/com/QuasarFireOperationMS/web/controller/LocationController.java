package com.QuasarFireOperationMS.web.controller;

import com.QuasarFireOperationMS.service.LocationService;
import com.QuasarFireOperationMS.web.model.LocationInfoDto;
import com.QuasarFireOperationMS.web.model.PositionDto;
import com.QuasarFireOperationMS.web.model.SatellitesDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

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

    @PostMapping(path = "/topsecret/")
    public ResponseEntity<LocationInfoDto> handlePost(@Valid @NotNull @RequestBody SatellitesDto satellitesDto) {

        HttpHeaders headers = new HttpHeaders();
        LocationInfoDto locationInfoDto = locationService.calculateLocationFromSatellitesGroup(satellitesDto);
        return new ResponseEntity<LocationInfoDto>(locationInfoDto, headers, HttpStatus.OK);
    }
}
