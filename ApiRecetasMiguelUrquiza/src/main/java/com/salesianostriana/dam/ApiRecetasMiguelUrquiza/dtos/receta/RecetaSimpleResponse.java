package com.salesianostriana.dam.ApiRecetasMiguelUrquiza.dtos.receta;

import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.models.Receta;

public record RecetaSimpleResponse(
        String nombre,
        int tiempoPreparacionMin
) {

    public static RecetaSimpleResponse of(Receta receta){
        return new RecetaSimpleResponse(
                receta.getNombre(),
                receta.getTiempoPreparacionMin()
        );
    }

}
