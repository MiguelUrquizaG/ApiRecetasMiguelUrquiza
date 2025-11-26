package com.salesianostriana.dam.ApiRecetasMiguelUrquiza.dtos.ingrediente;

import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.dtos.ingrediente_receta.IngredienteRecetaResponse;
import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.dtos.receta.RecetaResponse;
import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.models.Ingrediente;

import java.util.List;

public record IngredienteResponse(
        Long id,
        String nombre,
        List<IngredienteRecetaResponse> ingredientesRecetas
) {

    public static IngredienteResponse of(Ingrediente ingrediente){
        return new IngredienteResponse(
                ingrediente.getId(),
                ingrediente.getNombre(),
                ingrediente.getIngredientesRecetas().stream().map(IngredienteRecetaResponse::of).toList()
        );
    }
}
