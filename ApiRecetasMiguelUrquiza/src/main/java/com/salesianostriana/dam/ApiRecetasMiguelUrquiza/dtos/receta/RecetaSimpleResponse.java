package com.salesianostriana.dam.ApiRecetasMiguelUrquiza.dtos.receta;

import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.models.Receta;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RecetaSimpleResponse(
        @Schema(description = "Nombre de la Receta",example = "Pato al horno") @NotBlank String nombre,
        @Schema(description = "Tiempo de preparación",example = "12") @NotNull @Min(0) int tiempoPreparacionMin
) {

    public static RecetaSimpleResponse of(Receta receta){
        return new RecetaSimpleResponse(
                receta.getNombre(),
                receta.getTiempoPreparacionMin()
        );
    }

}
