package com.salesianostriana.dam.ApiRecetasMiguelUrquiza.dtos;

import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.models.Ingrediente;
import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.models.IngredientesReceta;
import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.models.Receta;
import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.models.TipoUnidad;

public record IngredienteRecetaResponse(
        Long id,
        int cantidad,
        Ingrediente ingrediente,
        Receta receta,
        TipoUnidad unidad
) {

    public IngredienteRecetaResponse of (IngredientesReceta ingredientesReceta){
        return new IngredienteRecetaResponse(
                ingredientesReceta.getId(),
                ingredientesReceta.getCantidad(),
                ingredientesReceta.getIngrediente(),
                ingredientesReceta.getReceta(),
                ingredientesReceta.getUnidad()
        );
    }
}
