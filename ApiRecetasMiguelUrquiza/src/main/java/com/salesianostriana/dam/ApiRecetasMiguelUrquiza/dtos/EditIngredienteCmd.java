package com.salesianostriana.dam.ApiRecetasMiguelUrquiza.dtos;

import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.models.Ingrediente;
import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.models.IngredientesReceta;
import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.models.Receta;

import java.util.List;

public record EditIngredienteCmd(
        String nombre,
        List<IngredientesReceta> ingredientesRecetas
) {

    public Ingrediente toEntity (EditIngredienteCmd cmd){
        return Ingrediente.builder()
                .nombre(cmd.nombre)
                .ingredientesRecetas(cmd.ingredientesRecetas)
                .build();
    }
}
