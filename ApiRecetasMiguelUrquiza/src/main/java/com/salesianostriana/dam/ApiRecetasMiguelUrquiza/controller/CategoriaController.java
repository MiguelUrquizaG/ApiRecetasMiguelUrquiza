package com.salesianostriana.dam.ApiRecetasMiguelUrquiza.controller;

import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.dtos.categoria.CategoriaResponse;
import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.dtos.categoria.EditCategoriaCmd;
import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.models.Categoria;
import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.services.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;


    @GetMapping("")
    public List<CategoriaResponse> getAll(){

        return categoriaService.getAll().stream()
                .map(CategoriaResponse::of)
                .toList();

    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponse> getById(@PathVariable Long id){
        return ResponseEntity.ok(
                CategoriaResponse.of(categoriaService.getById(id))
        );
    }

    @PostMapping("")
    public ResponseEntity<CategoriaResponse> create(@RequestBody EditCategoriaCmd cmd){

        Categoria nuevaCategoria = categoriaService.save(cmd);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CategoriaResponse.of(nuevaCategoria));

    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaResponse> edit(@PathVariable Long id,@RequestBody EditCategoriaCmd cmd){
        return ResponseEntity.ok(CategoriaResponse.of(categoriaService.edit(cmd,id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?>delete(@PathVariable Long id){
        categoriaService.deleteById(id);

        return ResponseEntity.noContent().build();
    }


}
