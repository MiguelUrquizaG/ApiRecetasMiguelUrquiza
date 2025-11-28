package com.salesianostriana.dam.ApiRecetasMiguelUrquiza.dtos.ingrediente;

import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.models.Ingrediente;
import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.models.IngredientesReceta;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.ArrayList;

public record EditIngredienteCmd(
        @Schema(description = "El nombre de un ingrediente",example = "Queso") String nombre
) {

    public Ingrediente toEntity (EditIngredienteCmd cmd){
        return Ingrediente.builder()
                .nombre(cmd.nombre)//Preguntar como gestionar esto
                .build();
    }
}
