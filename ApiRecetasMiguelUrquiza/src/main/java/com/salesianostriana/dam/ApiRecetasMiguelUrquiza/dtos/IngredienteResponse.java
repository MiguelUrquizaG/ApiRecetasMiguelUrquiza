package com.salesianostriana.dam.ApiRecetasMiguelUrquiza.dtos;

import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.models.Ingrediente;
import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.models.Receta;

import java.util.List;

public record IngredienteResponse(
        Long id,
        String nombre,
        List<Receta> composicion
) {

    public IngredienteResponse of(Ingrediente ingrediente){
        return new IngredienteResponse(
                ingrediente.getId(),
                ingrediente.getNombre(),
                ingrediente.getComposicion()
        );
    }
}
