package com.salesianostriana.dam.ApiRecetasMiguelUrquiza.dtos.ingrediente;

import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.dtos.ingrediente_receta.IngredienteRecetaResponse;
import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.dtos.receta.RecetaResponse;
import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.models.Ingrediente;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public record IngredienteResponse(
        @Schema(description = "Identificador del ingrediente",example = "1") Long id,
        @Schema(description = "Nombre de un ingrediente",example = "Pan") String nombre,
        @ArraySchema(arraySchema = @Schema(description = "Es la lista de la tabla intermedia Ingredientes-Receta",implementation = IngredienteRecetaResponse.class))
        List<IngredienteRecetaResponse> ingredientesRecetas
) {

    public static IngredienteResponse of(Ingrediente ingrediente){
        return new IngredienteResponse(
                ingrediente.getId(),
                ingrediente.getNombre(),
                ingrediente.getIngredientesRecetas().stream().map(IngredienteRecetaResponse::of).toList()
                //Mejorable usando un dto que solo me devuelva ID, Cantidad, Unidad y Receta.
        );
    }
}
