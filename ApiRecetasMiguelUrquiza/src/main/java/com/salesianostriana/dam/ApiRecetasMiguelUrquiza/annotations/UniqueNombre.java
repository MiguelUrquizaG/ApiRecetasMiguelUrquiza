package com.salesianostriana.dam.ApiRecetasMiguelUrquiza.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;
import java.time.LocalDate;

@Target({ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueNombreValidator.class)
@Documented
public @interface UniqueNombre {

        String message() default "El nombre de la categoría ya existe";
        Class<?>[] groups() default {};
        Class<? extends Payload>[] payload() default {};



}
