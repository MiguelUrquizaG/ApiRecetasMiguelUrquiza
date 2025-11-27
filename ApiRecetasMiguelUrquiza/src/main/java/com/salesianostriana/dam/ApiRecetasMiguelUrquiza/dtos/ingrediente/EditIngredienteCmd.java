package com.salesianostriana.dam.ApiRecetasMiguelUrquiza.dtos.ingrediente;

import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.models.Ingrediente;
import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.models.IngredientesReceta;

import java.util.ArrayList;

public record EditIngredienteCmd(
        String nombre
) {

    public Ingrediente toEntity (EditIngredienteCmd cmd){
        return Ingrediente.builder()
                .nombre(cmd.nombre)
                .ingredientesRecetas(new ArrayList<IngredientesReceta>())//Preguntar como gestionar esto
                .build();
    }
}
