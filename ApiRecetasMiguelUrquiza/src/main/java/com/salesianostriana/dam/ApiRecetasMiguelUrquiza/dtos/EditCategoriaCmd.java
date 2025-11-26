package com.salesianostriana.dam.ApiRecetasMiguelUrquiza.dtos;


import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.models.Categoria;
import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.models.Receta;
import jakarta.persistence.ManyToOne;

import java.util.List;

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
