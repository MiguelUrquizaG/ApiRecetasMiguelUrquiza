package com.salesianostriana.dam.ApiRecetasMiguelUrquiza.dtos;

import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.enums.dificultad;
import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.models.Categoria;
import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.models.Ingrediente;
import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.models.Receta;

import java.util.List;

public record RecetaResponse(
        Long id,
        String nombre,
        int tiempoPreparacionMin,
        dificultad dificultad,
        Categoria categoria,
        List<Ingrediente> listaIngredientes
) {

    public RecetaResponse of (Receta receta){
        return new RecetaResponse(
                receta.getId(),
                receta.getNombre(),
                receta.getTiempoPreparacionMin(),
                receta.getDificultad(),
                receta.getCategoria(),
                receta.getListaIngredientes()
        );
    }
}
