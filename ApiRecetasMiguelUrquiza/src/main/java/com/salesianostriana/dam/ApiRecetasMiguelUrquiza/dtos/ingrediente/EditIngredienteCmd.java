package com.salesianostriana.dam.ApiRecetasMiguelUrquiza.dtos.ingrediente;

import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.models.Ingrediente;

public record EditIngredienteCmd(
        String nombre
) {

    public Ingrediente toEntity (EditIngredienteCmd cmd){
        return Ingrediente.builder()
                .nombre(cmd.nombre)
                .build();
    }
}
