package com.salesianostriana.dam.ApiRecetasMiguelUrquiza.annotations;

import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.respositories.CategoriaRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

public class UniqueNombreValidator implements ConstraintValidator<UniqueNombre,String> {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return StringUtils.hasText(s) && !categoriaRepository.existsByNombre(s);
    }
}
