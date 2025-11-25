package com.salesianostriana.dam.ApiRecetasMiguelUrquiza.dtos;

import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.models.*;

import java.util.List;

public record RecetaResponse(
        Long id,
        String nombre,
        int tiempoPreparacionMin,
        Dificultad dificultad,
        Categoria categoria,
        List<IngredientesReceta> listaIngredientes
) {

    public RecetaResponse of (Receta receta){
        return new RecetaResponse(
                receta.getId(),
                receta.getNombre(),
                receta.getTiempoPreparacionMin(),
                receta.getDificultad(),
                receta.getCategoria(),
                listaIngredientes()
        );
    }
}
