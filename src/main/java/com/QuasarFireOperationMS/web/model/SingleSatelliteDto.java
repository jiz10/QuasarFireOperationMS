package com.QuasarFireOperationMS.web.model;

import com.QuasarFireOperationMS.web.model.validator.MessageValidator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

/**
 * @author jiz10
 * 1/5/21
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SingleSatelliteDto {

    @NotNull
    @Positive
    double distance;

    @NotNull
    @MessageValidator
    List<String> message;

}
