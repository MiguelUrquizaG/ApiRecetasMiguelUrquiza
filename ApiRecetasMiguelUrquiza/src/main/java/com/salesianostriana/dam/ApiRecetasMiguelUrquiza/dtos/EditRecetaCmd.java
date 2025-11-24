package com.salesianostriana.dam.ApiRecetasMiguelUrquiza.dtos;

import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.enums.dificultad;
import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.models.Categoria;
import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.models.Ingrediente;
import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.models.Receta;
import jakarta.persistence.EnumType;


import java.util.List;

public record EditRecetaCmd(
        String nombre,
        int tiempoPreparacionMin,
        dificultad dificultad,
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
