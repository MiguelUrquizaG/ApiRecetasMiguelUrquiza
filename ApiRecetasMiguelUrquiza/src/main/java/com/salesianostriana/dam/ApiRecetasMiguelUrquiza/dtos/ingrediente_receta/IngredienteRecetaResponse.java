package com.salesianostriana.dam.ApiRecetasMiguelUrquiza.dtos.ingrediente_receta;

import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.models.IngredientesReceta;
import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.models.TipoUnidad;
import io.swagger.v3.oas.annotations.media.Schema;

public record IngredienteRecetaResponse(
        @Schema(description = "Identificador de la relación.",example = "1") Long id,
        @Schema(description = "Cantidad del ingrediente.",example = "125") int cantidad,
        @Schema(description = "Nombre del ingrediente",example = "Queso") String nombreIngrediente,
        @Schema(description = "Nombre de la receta",example = "Hamburguesa") String nombreReceta,
        @Schema(description = "Enum tipo de unidad.",example = "GRAMOS") TipoUnidad unidad
) {

    //Preguntar si hacer DTOs Simples, para cada relación en este caso ingrediente y Receta.
    public static IngredienteRecetaResponse of (IngredientesReceta ingredientesReceta){
        return new IngredienteRecetaResponse(
                ingredientesReceta.getId(),
                ingredientesReceta.getCantidad(),
                ingredientesReceta.getIngrediente().getNombre(),
                ingredientesReceta.getReceta().getNombre(),
                ingredientesReceta.getUnidad()
        );
    }


}
