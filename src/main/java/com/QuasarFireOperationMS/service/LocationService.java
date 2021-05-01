package com.QuasarFireOperationMS.service;

import com.QuasarFireOperationMS.web.model.LocationInfoDto;
import com.QuasarFireOperationMS.web.model.SatellitesDto;
import com.QuasarFireOperationMS.web.model.SingleSatelliteDto;
import com.QuasarFireOperationMS.web.model.SingleSatelliteInfoDto;

/**
 * @author jiz10
 * 30/4/21
 */
public interface LocationService {

    public LocationInfoDto getLocationFromSatellitesGroup(SatellitesDto satellitesDto);


    SingleSatelliteInfoDto saveSatelliteInfo(SingleSatelliteDto singleSatelliteDto, String satellite_name);

    LocationInfoDto getLocationSplit();
}
