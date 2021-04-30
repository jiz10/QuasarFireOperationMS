package com.QuasarFireOperationMS.service;

import com.QuasarFireOperationMS.web.model.LocationInfoDto;
import com.QuasarFireOperationMS.web.model.SatellitesDto;

/**
 * @author jiz10
 * 30/4/21
 */
public interface LocationService {

    public LocationInfoDto calculateLocationFromSatellitesGroup(SatellitesDto satellitesDto);


}
