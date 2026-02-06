package com.salesianostriana.dam.ApiRecetasMiguelUrquiza.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class DateBetweenValidator implements ConstraintValidator<DateTimeBetween, LocalDateTime> {


    private String strMinDate,strMaxDate;

    //Nos permite sacar datos de la anotacion
    @Override
    public void initialize(DateTimeBetween constraintAnnotation) {
        strMaxDate = constraintAnnotation.max();
        strMinDate = constraintAnnotation.min();
    }

    @Override
    public boolean isValid(LocalDateTime localDateTime, ConstraintValidatorContext constraintValidatorContext) {
        LocalDateTime minDate = LocalDateTime.parse(strMinDate);
        LocalDateTime maxDate = LocalDateTime.parse(strMaxDate);

        return localDateTime!=null &&localDateTime.isAfter(minDate)&& localDateTime.isBefore(maxDate);
    }
}
