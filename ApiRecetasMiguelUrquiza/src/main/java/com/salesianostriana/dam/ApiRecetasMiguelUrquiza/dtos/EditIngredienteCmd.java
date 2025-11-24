package com.salesianostriana.dam.ApiRecetasMiguelUrquiza.dtos;

import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.models.Ingrediente;
import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.models.Receta;

import java.util.List;

public record EditIngredienteCmd(
        String nombre,
        List<Receta> listaRecetas
) {

    public Ingrediente toEntity (EditIngredienteCmd cmd){
        return Ingrediente.builder()
                .nombre(cmd.nombre)
                .listaRecetas(cmd.listaRecetas)
                .build();
    }
}
