package com.salesianostriana.dam.ApiRecetasMiguelUrquiza.dtos;

import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.models.Ingrediente;
import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.models.IngredientesReceta;
import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.models.Receta;
import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.models.TipoUnidad;
import jakarta.persistence.ManyToOne;

public record IngredienteRecetaCmd(

        int cantidad,
        Ingrediente ingrediente,
        Receta receta,
        TipoUnidad unidad
) {

    public IngredientesReceta toEntity(IngredienteRecetaCmd cmd){
        return IngredientesReceta.builder()
                .cantidad(cantidad)
                .ingrediente(ingrediente)
                .receta(receta)
                .unidad(unidad)
                .build();
    }

}
