package com.salesianostriana.dam.ApiRecetasMiguelUrquiza.dtos;

import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.models.Dificultad;
import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.models.Categoria;
import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.models.Ingrediente;
import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.models.Receta;


import java.util.List;

public record EditRecetaCmd(
        String nombre,
        int tiempoPreparacionMin,
        Dificultad dificultad,
        Categoria categoria,
        List<Ingrediente>listaIngredientes
) {

    public Receta toEntity (EditRecetaCmd cmd){
        return Receta.builder()
                .nombre(cmd.nombre)
                .tiempoPreparacionMin(cmd.tiempoPreparacionMin)
                .dificultad(cmd.dificultad)
                .categoria(cmd.categoria)
                .listaIngredientes(cmd.listaIngredientes)
                .build();
    }
}
