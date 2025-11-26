package com.salesianostriana.dam.ApiRecetasMiguelUrquiza.dtos.receta;

import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.dtos.ingrediente_receta.IngredienteRecetaResponse;
import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.models.*;

import java.util.List;

public record RecetaResponse(
        Long id,
        String nombre,
        int tiempoPreparacionMin,
        Dificultad dificultad,
        String nombreCategoria,
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
