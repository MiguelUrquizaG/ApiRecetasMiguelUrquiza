package com.salesianostriana.dam.ApiRecetasMiguelUrquiza.dtos;

import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.models.Categoria;
import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.models.Receta;

import java.util.List;

public record CategoriaResponse(
        Long id,
        String nombre,
        String descripcion,
        List<Receta> listaRecetas
) {

    public CategoriaResponse of(Categoria categoria){
        return new CategoriaResponse(
                categoria.getId(),
                categoria.getNombre(),
                categoria.getDescripcion(),
                categoria.getListaRecetas()
        );
    }
}
