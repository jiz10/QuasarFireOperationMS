package com.QuasarFireOperationMS.web.model;

import com.QuasarFireOperationMS.web.model.validator.SatelliteListValidator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author jiz10
 * 30/4/21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SatellitesDto {

    @NotNull
    @SatelliteListValidator
    List<SatelliteDto> satellites;

}
