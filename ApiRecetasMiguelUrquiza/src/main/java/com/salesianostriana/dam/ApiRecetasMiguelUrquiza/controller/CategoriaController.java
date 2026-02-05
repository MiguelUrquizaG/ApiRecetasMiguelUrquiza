package com.salesianostriana.dam.ApiRecetasMiguelUrquiza.controller;

import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.dtos.categoria.CategoriaResponse;
import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.dtos.categoria.EditCategoriaCmd;
import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.models.Categoria;
import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.services.CategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categorias")
@Tag(name = "Categorías",description = "Este controller maneja todas las operaciones con categorías.")
public class CategoriaController {

    private final CategoriaService categoriaService;


    @Operation(summary = "Obtiene todas las categorías")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200",description = "Se han encontrado las categorías"
                    ,content = @Content(mediaType = "application/json"
                    ,array = @ArraySchema(schema = @Schema(implementation = CategoriaResponse.class))
                    ,examples = @ExampleObject("""
                            
                            [
                                {
                                    "id": 1,
                                    "nombre": "Cocina Francesa",
                                    "descripcion": "No me gusta Francia",
                                    "listaRecetas": []
                                }
                            ]
                            
                            """)
                        )
                    ),
                    @ApiResponse(responseCode = "404",description = "No se han encontrado categorías."
                    ,content = @Content(mediaType = "application/json"
                    ,schema = @Schema(implementation = ProblemDetail.class)
                    ,examples = @ExampleObject("""
                            
                            {
                                "type": "about:blank",
                                "title": "Entidad no encontrada",
                                "status": 404,
                                "detail": "No se ha encontrado la categoría",
                                "instance": "/categorias"
                            }
                            """)
                        )
                    )

            })
    @GetMapping("")
    public List<CategoriaResponse> getAll(){

        return categoriaService.getAll().stream()
                .map(CategoriaResponse::of)
                .toList();

    }
    @Operation(summary = "Obtiene una categoría en base al id otorgado.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200",description = "Categoría encontrada"
                    ,content = @Content(mediaType = "application/json"
                    ,schema = @Schema(implementation = CategoriaResponse.class)
                    ,examples = @ExampleObject("""
                            {
                                "id": 1,
                                "nombre": "Cocina A",
                                "descripcion": "No me gusta Francia",
                                "listaRecetas": []
                            }
                            """)
                        )
                    ),
                    @ApiResponse(responseCode = "404",description = "No se ha encontrado la categoría."
                    ,content = @Content(mediaType = "application/json"
                    ,schema = @Schema(implementation = ProblemDetail.class)
                    ,examples = @ExampleObject("""
                            
                            {
                                "type": "about:blank",
                                "title": "Entidad no encontrada",
                                "status": 404,
                                "detail": "No se ha encontrado una categoría con el id: 2",
                                "instance": "/categorias/2"
                            }
                            """)
                        )
                    )
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponse> getById(@PathVariable Long id){
        return ResponseEntity.ok(
                CategoriaResponse.of(categoriaService.getById(id))
        );
    }

    @Operation(summary = "Crea una categoría según los datos otorgados, siempre y cuando sean válidos.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201",description = "Se ha creado la categoría."
                    ,content = @Content(mediaType = "application/json"
                    ,schema = @Schema(implementation = CategoriaResponse.class)
                    ,examples = @ExampleObject("""
                            {
                                "id": 1,
                                "nombre": "Cocina A",
                                "descripcion": "No me gusta Francia",
                                "listaRecetas": []
                            }
                            """)
                        )
                    ),
                    @ApiResponse(responseCode = "409",description = "Ya existe una categoría con ese nombre."
                    ,content = @Content(mediaType = "application/json"
                    ,schema = @Schema(implementation = ProblemDetail.class)
                    ,examples = @ExampleObject("""
                            {
                                "type": "about:blank",
                                "title": "Conflicto con la entidad",
                                "status": 409,
                                "detail": "Ya existe una categoria con ese nombre.",
                                "instance": "/categorias"
                            }
                            """)
                        )
                    )
            }
    )
    @PostMapping("")
    public ResponseEntity<CategoriaResponse> create(@Valid @RequestBody EditCategoriaCmd cmd){

        Categoria nuevaCategoria = categoriaService.save(cmd);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CategoriaResponse.of(nuevaCategoria));

    }

    @Operation(summary = "Edita una categoría seguún los datos otorgados y el id.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200",description = "Se ha modificado correctamente la categoría."
                    ,content = @Content(mediaType = "application/json"
                    ,schema = @Schema(implementation = CategoriaResponse.class)
                    ,examples = @ExampleObject("""
                            
                            {
                                "id": 1,
                                "nombre": "Cocina Belga",
                                "descripcion": "Si me gusta Belgica",
                                "listaRecetas": []
                            }
                            
                            """)
                        )
                    ),
                    @ApiResponse(responseCode = "404",description = "No se ha encontrado la categoría."
                    ,content = @Content(mediaType = "application/json"
                    ,schema = @Schema(implementation = ProblemDetail.class)
                    ,examples = @ExampleObject("""
                            
                            {
                                "type": "about:blank",
                                "title": "Entidad no encontrada",
                                "status": 404,
                                "detail": "No se ha encontrado una categoría con el id: 2",
                                "instance": "/categorias/2"
                            }
                            """)
                        )
                    ),
                    @ApiResponse(responseCode = "409",description = "Ya existe una categoría con ese nombre."
                    ,content = @Content(mediaType = "application/json"
                    ,schema = @Schema(implementation = ProblemDetail.class)
                    ,examples = @ExampleObject("""
                            {
                                "type": "about:blank",
                                "title": "Conflicto con la entidad",
                                "status": 409,
                                "detail": "Ya existe una categoria con ese nombre.",
                                "instance": "/categorias/1"
                            }
                            """)
                        )
                    )
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<CategoriaResponse> edit(@PathVariable Long id,@RequestBody EditCategoriaCmd cmd){
        return ResponseEntity.ok(CategoriaResponse.of(categoriaService.edit(cmd,id)));
    }

    @Operation(summary = "Elimina la categoría en base al id.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "204",description = "Cuerpo Vacío"),
                    @ApiResponse(responseCode = "404",description = "No se ha encontrado la categoría a eliminar."
                    ,content = @Content(mediaType = "application/json"
                    ,schema = @Schema(implementation = ProblemDetail.class)
                    ,examples = @ExampleObject("""
                            
                             {
                                 "type": "about:blank",
                                 "title": "Entidad no encontrada",
                                 "status": 404,
                                 "detail": "No se encuentra la categoría para eliminarla",
                                 "instance": "/categorias/4"
                             }
                            
                            """)
                        )
                    ),
                    @ApiResponse(responseCode = "409",description = "No se puede eliminar una categoria con recetas asociadas."
                    ,content = @Content(mediaType = "application/json"
                    ,schema = @Schema(implementation = ProblemDetail.class)
                    ,examples = @ExampleObject("""
                            {
                                "type": "about:blank",
                                "title": "Entidad no encontrada",
                                "status": 409,
                                "detail": "No se puede eliminar una categoría con recetas Asociadas.",
                                "instance": "/categorias/1"
                            }
                            """)
                        )
                    )

            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<?>delete(@PathVariable Long id){
        categoriaService.deleteById(id);

        return ResponseEntity.noContent().build();
    }


}
