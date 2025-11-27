package com.salesianostriana.dam.ApiRecetasMiguelUrquiza.services;

import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.dtos.ingrediente_receta.IngredienteRecetaCmd;
import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.models.Ingrediente;
import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.models.IngredientesReceta;
import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.models.Receta;
import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.respositories.IngredienteRecetaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Lazy
public class IngredienteRecetaService {

    private final IngredienteRecetaRepository ingredienteRecetaRepository;
    private final IngredienteService ingredienteService;
    @Lazy
    private final RecetaService recetaService;


    public IngredientesReceta save(IngredienteRecetaCmd cmd){

        Ingrediente ingrediente = ingredienteService.getById(cmd.idIngrediente());
        Receta receta = recetaService.getById(cmd.idReceta());

        return ingredienteRecetaRepository.save(IngredientesReceta.builder()
                .cantidad(cmd.cantidad())
                .ingrediente(ingrediente)
                .receta(receta)
                .unidad(cmd.unidad())
                .build());


    }

    public IngredientesReceta edit(IngredienteRecetaCmd cmd,Long id){

    }

    public void deleteById(Long id){

    }


}
