package com.salesianostriana.dam.ApiRecetasMiguelUrquiza.dtos.receta;

import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.models.*;
import io.swagger.v3.oas.annotations.media.Schema;

public record EditRecetaCmd(
        @Schema(description = "Nombre de la receta",example = "Pasta a la carbonara") String nombre,
        @Schema(description = "Tiempo de preparación",example = "10") int tiempoPreparacionMin,
        @Schema(description = "Enum que define la dificultad de la receta",example = "DIFICIL") Dificultad dificultad,
        @Schema(description = "Identificador de la categoría.",example = "1") Long idCategoria
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
