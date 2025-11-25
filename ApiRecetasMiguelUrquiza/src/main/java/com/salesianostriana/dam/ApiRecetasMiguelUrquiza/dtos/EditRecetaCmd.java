package com.salesianostriana.dam.ApiRecetasMiguelUrquiza.dtos;

import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.models.*;


import java.util.List;

public record EditRecetaCmd(
        String nombre,
        int tiempoPreparacionMin,
        Dificultad dificultad,
        Categoria categoria,
        List<IngredientesReceta>ingredientesRecetas
) {

    public Receta toEntity (EditRecetaCmd cmd){
        return Receta.builder()
                .nombre(cmd.nombre)
                .tiempoPreparacionMin(cmd.tiempoPreparacionMin)
                .dificultad(cmd.dificultad)
                .categoria(cmd.categoria)
                .ingredientesRecetas(cmd.ingredientesRecetas)
                .build();
    }
}
