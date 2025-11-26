package com.salesianostriana.dam.ApiRecetasMiguelUrquiza.controller;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.dtos.ingrediente.EditIngredienteCmd;
import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.dtos.ingrediente.IngredienteResponse;
import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.services.IngredienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import java.util.List;

@RestController
@RequestMapping("/ingredientes")
@RequiredArgsConstructor
public class IngredienteController {

    private final IngredienteService ingredienteService;


    @GetMapping("")
    public List<IngredienteResponse> getAll(){
        return ingredienteService.getAll().stream()
                .map(IngredienteResponse::of).toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<IngredienteResponse> getById(@PathVariable Long id){
        return ResponseEntity.ok(IngredienteResponse.of(ingredienteService.getById(id)));
    }

    @PostMapping("")
    public ResponseEntity<IngredienteResponse>create(@RequestBody EditIngredienteCmd cmd){
        return ResponseEntity.status(HttpStatus.CREATED).body(IngredienteResponse.of(ingredienteService.save(cmd)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<IngredienteResponse>edit(@PathVariable Long id,@RequestBody EditIngredienteCmd cmd){
        return ResponseEntity.ok(IngredienteResponse.of(ingredienteService.edit(cmd,id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?>deleteById(@PathVariable Long id){
        ingredienteService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
