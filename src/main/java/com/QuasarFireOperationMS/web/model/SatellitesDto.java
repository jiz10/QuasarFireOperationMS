package com.QuasarFireOperationMS.web.model;

import lombok.*;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Max;
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

    @NotNull(message = "Satellites is mandatory")
    List<SatelliteDto> satellites;

}
