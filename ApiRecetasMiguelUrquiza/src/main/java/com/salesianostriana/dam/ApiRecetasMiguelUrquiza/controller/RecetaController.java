package com.salesianostriana.dam.ApiRecetasMiguelUrquiza.controller;


import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.dtos.receta.RecetaResponse;
import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.services.RecetaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

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

}
