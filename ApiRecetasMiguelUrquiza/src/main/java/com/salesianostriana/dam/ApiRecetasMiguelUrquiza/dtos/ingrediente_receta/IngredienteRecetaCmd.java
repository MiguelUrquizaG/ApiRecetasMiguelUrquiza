package com.salesianostriana.dam.ApiRecetasMiguelUrquiza.dtos.ingrediente_receta;

import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.models.TipoUnidad;
import io.swagger.v3.oas.annotations.media.Schema;

public record IngredienteRecetaCmd(

        @Schema(description = "Cantidad del ingrediente",examples = "200") int cantidad,
        @Schema(description = "Identificador del ingrediente",examples = "1") Long idIngrediente,
        @Schema(description = "Identificador de la receta",examples = "2") Long  idReceta,
        @Schema(description = "Enum que define el tipo de unidad.",example = "GRAMOS") TipoUnidad unidad
) {

   /*public IngredientesReceta toEntity(IngredienteRecetaCmd cmd){
        return IngredientesReceta.builder()
                .cantidad(cantidad)
                .ingrediente(ingrediente)
                .receta(receta)
                .unidad(unidad)
                .build();
    }*/

}
