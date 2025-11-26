package com.salesianostriana.dam.ApiRecetasMiguelUrquiza.controller;


import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.dtos.ingrediente_receta.IngredienteRecetaCmd;
import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.dtos.receta.EditRecetaCmd;
import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.dtos.receta.RecetaResponse;
import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.models.Receta;
import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.services.RecetaService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/recetas")
public class RecetaController {

    private final RecetaService recetaService;


    @GetMapping("")
    public List<RecetaResponse> getAll(){
        return recetaService.getAll().stream()
                .map(RecetaResponse::of)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecetaResponse> getById(@PathVariable Long id){
        return ResponseEntity.ok(RecetaResponse.of(recetaService.getById(id)));
    }


    @PostMapping("")
    public ResponseEntity<RecetaResponse>create(@RequestBody EditRecetaCmd cmd){
        Receta receta = recetaService.save(cmd);
        return ResponseEntity.status(HttpStatus.CREATED).body(RecetaResponse.of(receta));

    }

    @PutMapping("/{id}")
    public ResponseEntity<RecetaResponse> edit(@PathVariable Long id,@RequestBody EditRecetaCmd cmd){
        return ResponseEntity.ok(RecetaResponse.of(recetaService.edit(cmd,id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        recetaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/ingredientes")
    public ResponseEntity<RecetaResponse>addIngredienteToReceta(@PathVariable Long id, @RequestBody IngredienteRecetaCmd cmd){

        return ResponseEntity.ok(RecetaResponse.of(recetaService.addIngredienteToReceta(cmd,id)));

    }

}
