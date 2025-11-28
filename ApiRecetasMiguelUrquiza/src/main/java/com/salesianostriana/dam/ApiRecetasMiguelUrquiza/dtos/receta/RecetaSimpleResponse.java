package com.salesianostriana.dam.ApiRecetasMiguelUrquiza.dtos.receta;

import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.models.Receta;
import io.swagger.v3.oas.annotations.media.Schema;

public record RecetaSimpleResponse(
        @Schema(description = "Nombre de la Receta",example = "Pato al horno") String nombre,
        @Schema(description = "Tiempo de preparación",example = "12") int tiempoPreparacionMin
) {

    public static RecetaSimpleResponse of(Receta receta){
        return new RecetaSimpleResponse(
                receta.getNombre(),
                receta.getTiempoPreparacionMin()
        );
    }

}
