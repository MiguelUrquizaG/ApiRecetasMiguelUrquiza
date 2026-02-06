package com.salesianostriana.dam.ApiRecetasMiguelUrquiza.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;
import java.time.LocalDateTime;

@Target({ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DateBetweenValidator.class)
@Documented
public @interface DateTimeBetween {

    String message() default "La fecha y hora no estan en el rango establecido";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    String min();
    String max();
}
