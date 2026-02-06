package com.salesianostriana.dam.ApiRecetasMiguelUrquiza.dtos.categoria;


import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.annotations.DateTimeBetween;
import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.annotations.UniqueNombre;
import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.models.Categoria;
import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.models.Receta;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;
import java.util.ArrayList;

public record EditCategoriaCmd(

         @Schema(description = "Nombre de la categoría",example = "Cocina Francesa") @NotBlank(message = "{editCategoriaCmd.nombre.notblank}") @UniqueNombre() String nombre,
         @Schema(description = "Descripción de la categoría.",example = "Es un estilo muy raro.")@NotBlank String descripcion,
         @DateTimeBetween(max = "2026-02-10T00:00:00",min = "2020-01-01T00:00:00") LocalDateTime  fechaAlta

) {
    public Categoria toEntity(EditCategoriaCmd cmd){
        return  Categoria.builder()
                .nombre(cmd.nombre)
                .descripcion(cmd.descripcion)
                .build();
    }
}
