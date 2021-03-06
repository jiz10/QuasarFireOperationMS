package com.QuasarFireOperationMS.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    double distance;

    @NotNull
    List<String> message;
}
