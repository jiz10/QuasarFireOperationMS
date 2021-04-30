package com.QuasarFireOperationMS.web.model;

import lombok.*;

import javax.validation.constraints.NotBlank;
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
public class SatelliteDto {

    @NotNull(message = "Satellite name is mandatory")
    @NotBlank(message = "Satellite name is mandatory")
    private String name;

    @NotNull(message = "Distance is mandatory")
    @NotBlank(message = "Distance is mandatory")
    double distance;

    @NotNull(message = "Message is mandatory")
    List<String> message;
}
