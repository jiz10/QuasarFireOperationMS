package com.QuasarFireOperationMS.web.model;

import lombok.*;

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
public class PositionDto {
    @NotNull
    private Double x;
    @NotNull
    private Double y;
}
