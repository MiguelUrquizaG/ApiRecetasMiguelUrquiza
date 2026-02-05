package com.salesianostriana.dam.ApiRecetasMiguelUrquiza.dtos.categoria;


import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.models.Categoria;
import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.models.Receta;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;

public record EditCategoriaCmd(

         @Schema(description = "Nombre de la categoría",example = "Cocina Francesa") @NotBlank(message = "No puedes ser nulo") String nombre,
         @Schema(description = "Descripción de la categoría.",example = "Es un estilo muy raro.")@NotBlank String descripcion

) {
    public Categoria toEntity(EditCategoriaCmd cmd){
        return  Categoria.builder()
                .nombre(cmd.nombre)
                .descripcion(cmd.descripcion)
                .build();
    }
}
