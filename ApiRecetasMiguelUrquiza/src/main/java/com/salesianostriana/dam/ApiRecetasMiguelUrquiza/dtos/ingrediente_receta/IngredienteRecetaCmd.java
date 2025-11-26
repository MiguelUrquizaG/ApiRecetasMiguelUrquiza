package com.salesianostriana.dam.ApiRecetasMiguelUrquiza.dtos.ingrediente_receta;

import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.models.TipoUnidad;

public record IngredienteRecetaCmd(

        int cantidad,
        Long idIngrediente,
        Long  idReceta,
        TipoUnidad unidad
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
