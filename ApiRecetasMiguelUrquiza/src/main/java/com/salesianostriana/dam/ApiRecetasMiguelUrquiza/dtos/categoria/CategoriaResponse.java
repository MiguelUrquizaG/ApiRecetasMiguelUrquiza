package com.salesianostriana.dam.ApiRecetasMiguelUrquiza.dtos.categoria;

import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.dtos.receta.RecetaResponse;
import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.dtos.receta.RecetaSimpleResponse;
import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.models.Categoria;
import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.models.Receta;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public record CategoriaResponse(
        @Schema(description = "El identificador de la categoría devuelta",example = "1") Long id,
        @Schema(description = "Nombre de la categoría",example = "Cocina Italiana") String nombre,
        @Schema(description = "Descripción de la categoría",example = "La cocina italiana es muy buena.") String descripcion,
        @ArraySchema(arraySchema = @Schema(description = "Lista de Recetas ",implementation = RecetaSimpleResponse.class)) List<RecetaSimpleResponse> listaRecetas
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
