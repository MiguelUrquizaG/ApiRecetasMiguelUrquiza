package com.salesianostriana.dam.ApiRecetasMiguelUrquiza.controller;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.dtos.ingrediente.EditIngredienteCmd;
import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.dtos.ingrediente.IngredienteResponse;
import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.services.IngredienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import java.util.List;

@RestController
@RequestMapping("/ingredientes")
@RequiredArgsConstructor
@Tag(name = "Ingredientes", description = "Este controller maneja controla las operaciones con ingredientes")
public class IngredienteController {

    private final IngredienteService ingredienteService;

    @Operation(summary = "Obtiene todos los ingredientes.")
    @ApiResponses(
            value = {
            @ApiResponse(responseCode = "200", description = "Se han encontrado ingredientes.", content = @Content(mediaType = "application/json", array = @ArraySchema(
                    schema = @Schema(implementation = IngredienteResponse.class)
            ),
                    examples = @ExampleObject(value =
                            """
                                    [
                                        {
                                              "id": 1,
                                              "nombre": "Mantequilla",
                                              "ingredientesRecetas": [{
                                                                       "id": 1,
                                                                       "cantidad": 20,
                                                                       "nombreIngrediente": "Mantequilla",
                                                                       "nombreReceta": "Patatas",
                                                                       "unidad": "KILOS"
                                                                      }]
                                        }
                                    ]
                                    
                                    """)
            )),
            @ApiResponse(responseCode = "404", description = "No se ha encontrado el ingrediente"
            ,content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ProblemDetail.class),
                    examples = @ExampleObject("""
                            
                            {
                                "type": "about:blank",
                                "title": "Entidad no encontrada",
                                "status": 404,
                                "detail": "No se ha encontrado un ingrediente.",
                                "instance": "/ingredientes"
                            }
                            
                            """)
                )
            )
    })

    @GetMapping("")
    public List<IngredienteResponse> getAll() {
        return ingredienteService.getAll().stream()
                .map(IngredienteResponse::of).toList();
    }

    @Operation(summary = "Obtiene un ingrediente en base al id.")
    @ApiResponses(
           value = {
                @ApiResponse(responseCode = "200",description = "Se ha encontrado el ingrediente"
                        ,content =@Content(mediaType = "application/json",
                        schema = @Schema(implementation = IngredienteResponse.class),
                        examples = @ExampleObject("""
                                
                                {
                                    "id": 1,
                                    "nombre": "Cocina Francesa",
                                    "descripcion": "No me gusta Francia",
                                    "listaRecetas": []
                                }
                                
                                """)
                    )
                ),
                @ApiResponse(responseCode = "404",description = "No se ha encontrado un ingrediente con el id otorgado"
                        ,content = @Content(mediaType = "applicaiton/json"
                        ,schema =@Schema(implementation = ProblemDetail.class)
                        ,examples = @ExampleObject("""
                        
                        {
                            "type": "about:blank",
                            "title": "Entidad no encontrada",
                            "status": 404,
                            "detail": "No se ha encontrado un ingrediente con el id: 1",
                            "instance": "/ingredientes/1"
                        }
                        
                        """)
                    )
                )
            })
    @GetMapping("/{id}")
    public ResponseEntity<IngredienteResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(IngredienteResponse.of(ingredienteService.getById(id)));
    }

    @Operation(summary = "Crea un ingrediente según los parámetros.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201",description = "Se ha creado correctamente el ingrediente"
                    ,content = @Content(mediaType = "application/json"
                    ,schema = @Schema(implementation = IngredienteResponse.class)
                    ,examples = @ExampleObject("""
                            
                            {
                                "id": 3,
                                "nombre": "Papas",
                                "ingredientesRecetas": []
                            }
                            
                            
                            """)
                        )
                    ),
                    @ApiResponse(responseCode = "409",description = "No se ha podido crear el ingrediente ya que existe uno con ese nombre."
                    ,content = @Content(mediaType = "application/json"
                    ,schema = @Schema(implementation = ProblemDetail.class)
                    ,examples = @ExampleObject("""
                            
                            {
                                "type": "about:blank",
                                "title": "Conflicto con la entidad",
                                "status": 409,
                                "detail": "Ya existe un ingrediente con ese nombre",
                                "instance": "/ingredientes"
                            }
                            
                            """)
                        )
                    )
            })
    @PostMapping("")
    public ResponseEntity<IngredienteResponse> create(@RequestBody EditIngredienteCmd cmd) {
        return ResponseEntity.status(HttpStatus.CREATED).body(IngredienteResponse.of(ingredienteService.save(cmd)));
    }
    @Operation(summary = "Edita una categoría en base al id otorgado.")
     @ApiResponses(
             value = {

                     @ApiResponse(responseCode = "200",description = "Se ha modificado perfectamente el ingrediente."
                     ,content = @Content(mediaType = "application/json"
                     ,schema = @Schema(implementation = IngredienteResponse.class)
                     ,examples = @ExampleObject("""
                             
                             {
                                 "id": 2,
                                 "nombre": "Pan",
                                 "ingredientesRecetas": []
                             }
                             
                             """)
                        )
                     ),
                     @ApiResponse(responseCode = "404",description = "No se encuentra el ingrediente."
                     ,content = @Content(mediaType = "application/json"
                     ,schema = @Schema(implementation = ProblemDetail.class)
                     ,examples = @ExampleObject("""
                             
                             {
                                 "type": "about:blank",
                                 "title": "Entidad no encontrada",
                                 "status": 404,
                                 "detail": "No se ha encontrado un ingrediente con el id: 4",
                                 "instance": "/ingredientes/4"
                             }
                             
                             """)
                        )
                     ),
                     @ApiResponse(responseCode = "409",description = "Ya existe un ingrediente con ese nombre"
                     ,content = @Content(mediaType = "application/json"
                     ,schema = @Schema(implementation = ProblemDetail.class)
                     ,examples = @ExampleObject("""
                             
                             {
                                 "type": "about:blank",
                                 "title": "Conflicto con la entidad",
                                 "status": 409,
                                 "detail": "Ya existe un ingrediente con ese nombre",
                                 "instance": "/ingredientes/4"
                             }
                             """)
                        )
                     )


             })
    @PutMapping("/{id}")
    public ResponseEntity<IngredienteResponse> edit(@PathVariable Long id, @RequestBody EditIngredienteCmd cmd) {
        return ResponseEntity.ok(IngredienteResponse.of(ingredienteService.edit(cmd, id)));
    }
    @Operation(summary = "Elimina un ingrediente según el ID.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "204",description = "Cuerpo vacío"),
                    @ApiResponse(responseCode = "409",description = "No se puede eliminar un ingrediente relacionado a una receta."
                    ,content = @Content(mediaType = "application/json"
                    ,schema = @Schema(implementation = ProblemDetail.class)
                    ,examples = @ExampleObject("""
                            
                            {
                                "type": "about:blank",
                                "title": "Conflicto con la entidad",
                                "status": 409,
                                "detail": "No se puede eliminar un ingrediente relacionado a una receta.",
                                "instance": "/ingredientes/1"
                            }
                            
                            """)))
            }
    )

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        ingredienteService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
