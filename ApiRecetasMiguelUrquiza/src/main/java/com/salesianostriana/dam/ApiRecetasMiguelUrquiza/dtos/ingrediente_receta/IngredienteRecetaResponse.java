package com.salesianostriana.dam.ApiRecetasMiguelUrquiza.dtos.ingrediente_receta;

import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.models.IngredientesReceta;
import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.models.TipoUnidad;

public record IngredienteRecetaResponse(
        Long id,
        int cantidad,
        String nombreIngrediente,
        String nombreReceta,
        TipoUnidad unidad
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
