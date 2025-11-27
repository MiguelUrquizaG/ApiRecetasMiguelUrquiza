package com.salesianostriana.dam.ApiRecetasMiguelUrquiza.dtos.categoria;


import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.models.Categoria;
import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.models.Receta;

import java.util.ArrayList;

public record EditCategoriaCmd(

         String nombre,
         String descripcion

) {
    public Categoria toEntity(EditCategoriaCmd cmd){
        return  Categoria.builder()
                .nombre(cmd.nombre)
                .descripcion(cmd.descripcion)
                .build();
    }
}
