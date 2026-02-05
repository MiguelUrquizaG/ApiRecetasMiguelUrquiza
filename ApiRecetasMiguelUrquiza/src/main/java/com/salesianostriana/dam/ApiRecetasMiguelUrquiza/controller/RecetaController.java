package com.salesianostriana.dam.ApiRecetasMiguelUrquiza.controller;


import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.dtos.ingrediente_receta.IngredienteRecetaCmd;
import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.dtos.receta.EditRecetaCmd;
import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.dtos.receta.RecetaResponse;
import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.models.Receta;
import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.services.RecetaService;
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
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/recetas")
@Tag(name = "Recetas", description = "Este controller maneja toda la lógica de las recetas.")
public class RecetaController {

    private final RecetaService recetaService;


    @Operation(summary = "Obtiene todas las recetas.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Se han encontrado las recetas."
                            , content = @Content(mediaType = "application/json"
                            , array = @ArraySchema(schema = @Schema(implementation = RecetaResponse.class))
                            , examples = @ExampleObject("""
                            
                            [
                                {
                                    "id": 1,
                                    "nombre": "Patatas",
                                    "tiempoPreparacionMin": 1,
                                    "dificultad": "DIFICIL",
                                    "nombreCategoria": "Cocina A",
                                    "listaIngredientes": []
                                }
                            ]
                            """)
                    )
                    ),
                    @ApiResponse(responseCode = "404", description = "No se han encontrado las recetas."
                            , content = @Content(mediaType = "application/json"
                            , schema = @Schema(implementation = ProblemDetail.class)
                            , examples = @ExampleObject("""
                             {
                                "type": "about:blank",
                                "title": "Entidad no encontrada",
                                "status": 404,
                                "detail": "No se ha encontrado la receta buscada",
                                "instance": "/recetas"
                            }
                            """)
                    )
                    )
            }
    )
    @GetMapping("")
    public List<RecetaResponse> getAll() {
        return recetaService.getAll().stream()
                .map(RecetaResponse::of)
                .toList();
    }
    @Operation(summary = "Obtiene una receta según el ID.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Se ha encontrado la receta."
                            , content = @Content(mediaType = "application/json"
                            , schema = @Schema(implementation = RecetaResponse.class)
                            , examples = @ExampleObject("""
                            
                            {
                                "id": 1,
                                "nombre": "Patatas",
                                "tiempoPreparacionMin": 1,
                                "dificultad": "DIFICIL",
                                "nombreCategoria": "Cocina A",
                                "listaIngredientes": []
                            }
                            
                            """)
                    )
                    ),
                    @ApiResponse(responseCode = "404", description = "No se ha encontrado la receta."
                            , content = @Content(mediaType = "application/json"
                            , schema = @Schema(implementation = ProblemDetail.class)
                            , examples = @ExampleObject("""
                            {
                                "type": "about:blank",
                                "title": "Entidad no encontrada",
                                "status": 404,
                                "detail": "No se ha encontrado una categoría con el id: 2",
                                "instance": "/recetas/2"
                            }
                            """)
                    )
                    )
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<RecetaResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(RecetaResponse.of(recetaService.getById(id)));
    }

    @Operation(summary = "Crea una receta en base a los parámetros")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Se ha creado la receta"
                            , content = @Content(mediaType = "application/json"
                            , schema = @Schema(implementation = RecetaResponse.class)
                            , examples = @ExampleObject("""
                            {
                                "id": 2,
                                "nombre": "Pan",
                                "tiempoPreparacionMin": 1,
                                "dificultad": "DIFICIL",
                                "nombreCategoria": "Cocina A",
                                "listaIngredientes": []
                            }
                            """)
                    )
                    ),
                    @ApiResponse(responseCode = "400", description = "Error encontrar categoría/Error tiempo de preparación."
                            , content = @Content(mediaType = "application/json"
                            , schema = @Schema(implementation = ProblemDetail.class)
                            , examples = {@ExampleObject(
                            name = "CategoriaNoEncontrada",
                            summary = "No se ha encontrado categoria.",
                            value = """
                            {
                                "type": "about:blank",
                                "title": "Tiempo de preparación inválido",
                                "status": 400,
                                "detail": "No se puede crear la receta ya que no se ha encontrado la categoría.",
                                "instance": "/recetas"
                            }
                            """),
                            @ExampleObject(
                                    name = "TiempoInválido",
                                    summary = "Tiempo de preparación inválido",
                                    value = """
                                                            {
                                                "type": "about:blank",
                                                "title": "Tiempo de preparación inválido",
                                                "status": 400,
                                                "detail": "El tiempo de preparación no puede ser menor o igual que cero.",
                                                "instance": "/recetas"
                                            }
                                            """
                                )
                            }
                        )
                    ),
                    @ApiResponse(responseCode = "409", description = "Ya existe una receta con ese nombre."
                            , content = @Content(mediaType = "application/json"
                            , schema = @Schema(implementation = ProblemDetail.class)
                            , examples = @ExampleObject("""
                            {
                                "type": "about:blank",
                                "title": "Conflicto con la entidad",
                                "status": 409,
                                "detail": "Ya existe una receta con ese nombre.",
                                "instance": "/recetas"
                            }
                            """)
                    )
                    )

            }
    )
    @PostMapping("")
    public ResponseEntity<RecetaResponse> create(@Valid @RequestBody EditRecetaCmd cmd) {
        Receta receta = recetaService.save(cmd);
        return ResponseEntity.status(HttpStatus.CREATED).body(RecetaResponse.of(receta));

    }
    @Operation(summary = "Edita una receta según su ID y los parámetros requeridos.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Se ha editado la receta"
                            , content = @Content(mediaType = "application/json"
                            , schema = @Schema(implementation = RecetaResponse.class)
                            , examples = @ExampleObject("""
                            {
                                "id": 1,
                                "nombre": "Peces",
                                "tiempoPreparacionMin": 3,
                                "dificultad": "DIFICIL",
                                "nombreCategoria": "Cocina A",
                                "listaIngredientes": [
                                    {
                                        "id": 1,
                                        "cantidad": 20,
                                        "nombreIngrediente": "Agua",
                                        "nombreReceta": "Peces",
                                        "unidad": "KILOS"
                                    }
                                ]
                            }
                            """)
                    )
                    ),
                    @ApiResponse(responseCode = "400", description = "Error encontrar categoría/Error tiempo de preparación."
                            , content = @Content(mediaType = "application/json"
                            , schema = @Schema(implementation = ProblemDetail.class)
                            , examples = {@ExampleObject(
                            name = "CategoriaNoEncontrada",
                            summary = "No se ha encontrado categoria.",
                            value = """
                                    {
                                        "type": "about:blank",
                                        "title": "No se ha encontrado la categoría",
                                        "status": 400,
                                        "detail": "No se puede editar la receta ya que no se ha encontrado la categoría.",
                                        "instance": "/recetas"
                                    }
                                    """),
                            @ExampleObject(
                                    name = "TiempoInválido",
                                    summary = "Tiempo de preparación inválido",
                                    value = """
                                                            {
                                                "type": "about:blank",
                                                "title": "Tiempo de preparación inválido",
                                                "status": 400,
                                                "detail": "El tiempo de preparación no puede ser menor o igual que cero.",
                                                "instance": "/recetas"
                                            }
                                            """

                            )
                    }
                    )
                    ),
                    @ApiResponse(responseCode = "409", description = "Ya existe una receta con ese nombre."
                            , content = @Content(mediaType = "application/json"
                            , schema = @Schema(implementation = ProblemDetail.class)
                            , examples = @ExampleObject("""
                            {
                                "type": "about:blank",
                                "title": "Conflicto con la entidad",
                                "status": 409,
                                "detail": "Ya existe una receta con ese nombre.",
                                "instance": "/recetas"
                            }
                            """)
                    )
                    )
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<RecetaResponse> edit(@PathVariable Long id, @RequestBody EditRecetaCmd cmd) {
        return ResponseEntity.ok(RecetaResponse.of(recetaService.edit(cmd, id)));
    }


    @Operation(summary = "Elimina una receta según su ID.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "204", description = "Cuerpo Vacío"),
                    @ApiResponse(responseCode = "404", description = "No se ha encontrado la receta a eliminar."
                            , content = @Content(mediaType = "application/json"
                            , schema = @Schema(implementation = ProblemDetail.class)
                            , examples = @ExampleObject("""
                            {
                                "type": "about:blank",
                                "title": "Entidad no encontrada",
                                "status": 404,
                                "detail": "No se ha encontrado la receta que desea eliminar",
                                "instance": "/recetas/3"
                            }
                            
                            """)
                    )
                    )
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        recetaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Agrega un ingrediente en una receta.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200",description = "Se ha añadido el ingrediente a la receta."
                    ,content = @Content(mediaType = "application/json"
                    ,schema = @Schema(implementation = RecetaResponse.class)
                    ,examples = @ExampleObject("""
                            
                            {
                                "id": 1,
                                "nombre": "dasds",
                                "tiempoPreparacionMin": 1,
                                "dificultad": "DIFICIL",
                                "nombreCategoria": "Cocina A",
                                "listaIngredientes": [
                                    {
                                        "id": 1,
                                        "cantidad": 20,
                                        "nombreIngrediente": "Agua",
                                        "nombreReceta": "dasds",
                                        "unidad": "KILOS"
                                    }
                                ]
                            }
                            
                            """)
                        )
                    ),
                    @ApiResponse(responseCode = "400",description = "No se encuentra el ingrediente."
                    ,content = @Content(mediaType = "application/json"
                    ,schema = @Schema(implementation = ProblemDetail.class)
                    ,examples = @ExampleObject("""
                            
                            {
                                "type": "about:blank",
                                "title": "Requisitos inválidos",
                                "status": 400,
                                "detail": "No se puede agregar un ingrediente que no existe.",
                                "instance": "/recetas/1/ingredientes"
                            }
                            """)
                        )
                    ),
                    @ApiResponse(responseCode = "409",description = "No se puede agregar un ingrediente que ya está agregado."
                    ,content = @Content(mediaType = "application/json"
                    ,schema = @Schema(implementation = ProblemDetail.class)
                    ,examples = @ExampleObject(
                            """
                            {
                                "type": "about:blank",
                                "title": "Conflicto con la entidad",
                                "status": 409,
                                "detail": "Este ingrediente ya esta agregado en la receta.",
                                "instance": "/recetas/1/ingredientes"
                            }
                            
                            """)
                        )
                    )
            }
    )
    @PostMapping("/{id}/ingredientes")
    public ResponseEntity<RecetaResponse> addIngredienteToReceta(
            @PathVariable Long id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos del ingrediente a asignar a la receta(idIngrediente,cantidad y unidad)",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = IngredienteRecetaCmd.class),
                            examples = @ExampleObject(
                                    """
                                    {
                                        "idIngrediente":1,
                                        "cantidad":20,
                                        "unidad": "KILOS"
                                    }
                                    """
                            )
                    )
            )
            @RequestBody IngredienteRecetaCmd cmd) {

        return ResponseEntity.ok(RecetaResponse.of(recetaService.addIngredienteToReceta(cmd, id)));

    }

}
