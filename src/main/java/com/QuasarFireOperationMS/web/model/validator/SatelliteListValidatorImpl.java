package com.QuasarFireOperationMS.web.model.validator;

import com.QuasarFireOperationMS.web.model.SatelliteDto;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Value;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author jiz10
 * 30/4/21
 */
public class SatelliteListValidatorImpl implements ConstraintValidator<SatelliteListValidator, List<SatelliteDto>> {

    @Value("${satellites.names}")
    private String[] satellitesNames;

    @Override
    public void initialize(SatelliteListValidator constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(List<SatelliteDto> value, ConstraintValidatorContext context) {

        if (Objects.isNull(value))
            return false;

        if (value.size() != 3)
            return false;

        for (SatelliteDto satelliteDto : value) {
            if (Objects.isNull(satelliteDto.getDistance()) || satelliteDto.getDistance() <= 0)
                return false;

            if (Strings.isBlank(satelliteDto.getName()) || !Arrays.asList(satellitesNames).contains(satelliteDto.getName().toUpperCase()))
                return false;

            if (!(satelliteDto.getMessage().size() > 0))
                return false;
        }
        return true;
    }
}