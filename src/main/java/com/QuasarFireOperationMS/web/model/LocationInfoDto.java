package com.QuasarFireOperationMS.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author jiz10
 * 30/4/21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LocationInfoDto {
    
    @NotNull
    @NotBlank
    private PositionDto position;
    @NotNull
    @NotBlank
    private String message;
}
