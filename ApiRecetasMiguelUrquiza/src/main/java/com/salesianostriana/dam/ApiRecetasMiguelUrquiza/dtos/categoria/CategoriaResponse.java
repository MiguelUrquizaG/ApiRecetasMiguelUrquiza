package com.salesianostriana.dam.ApiRecetasMiguelUrquiza.dtos.categoria;

import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.dtos.receta.RecetaResponse;
import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.dtos.receta.RecetaSimpleResponse;
import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.models.Categoria;
import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.models.Receta;

import java.util.List;

public record CategoriaResponse(
        Long id,
        String nombre,
        String descripcion,
        List<RecetaSimpleResponse> listaRecetas
) {

    public static CategoriaResponse of(Categoria categoria){
        return new CategoriaResponse(
                categoria.getId(),
                categoria.getNombre(),
                categoria.getDescripcion(),
                categoria.getListaRecetas().stream().map(RecetaSimpleResponse::of).toList()
        );
    }
}
