package com.salesianostriana.dam.ApiRecetasMiguelUrquiza.dtos.receta;

import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.models.*;

public record EditRecetaCmd(
        String nombre,
        int tiempoPreparacionMin,
        Dificultad dificultad,
        Long idCategoria
) {

    /*public Receta toEntity (EditRecetaCmd cmd){
        return Receta.builder()
                .nombre(cmd.nombre)
                .tiempoPreparacionMin(cmd.tiempoPreparacionMin)
                .dificultad(cmd.dificultad)
                .categoria(cmd.idCategoria.)
                .ingredientesRecetas(cmd.ingredientesRecetas)
                .build();
    }*/
}
