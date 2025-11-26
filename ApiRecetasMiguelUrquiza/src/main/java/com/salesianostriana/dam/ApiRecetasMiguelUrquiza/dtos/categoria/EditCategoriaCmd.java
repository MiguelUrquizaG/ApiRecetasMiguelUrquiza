package com.salesianostriana.dam.ApiRecetasMiguelUrquiza.dtos.categoria;


import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.models.Categoria;

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
