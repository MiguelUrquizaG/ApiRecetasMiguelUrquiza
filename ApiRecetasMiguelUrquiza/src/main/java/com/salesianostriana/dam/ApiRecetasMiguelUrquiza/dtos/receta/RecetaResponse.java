package com.salesianostriana.dam.ApiRecetasMiguelUrquiza.dtos.receta;

import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.dtos.ingrediente_receta.IngredienteRecetaResponse;
import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.models.*;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public record RecetaResponse(
        @Schema(description = "Identificador de la receta.",example = "1") Long id,
        @Schema(description = "Nombre de la receta",example = "Pizza Margarita") String nombre,
        @Schema(description = "Tiempo de preparación",example = "14") int tiempoPreparacionMin,
        @Schema(description = "Enum que define la dificultad de la receta.",example = "FACIL") Dificultad dificultad,
        @Schema(description = "Nombre de la catergoría",example = "Cocina Italiana") String nombreCategoria,
        @ArraySchema(arraySchema = @Schema(description = "Lista de la tabla intermedia Ingredientes-Receta",implementation = RecetaResponse.class))
        List<IngredienteRecetaResponse> listaIngredientes
) {

    public static RecetaResponse of (Receta receta){
        return new RecetaResponse(
                receta.getId(),
                receta.getNombre(),
                receta.getTiempoPreparacionMin(),
                receta.getDificultad(),
                receta.getCategoria().getNombre(),
                receta.getIngredientesRecetas().stream().map(IngredienteRecetaResponse::of).toList()
        );
    }
}
