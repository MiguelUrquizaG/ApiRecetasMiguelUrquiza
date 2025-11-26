package com.salesianostriana.dam.ApiRecetasMiguelUrquiza.services;

import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.dtos.IngredienteRecetaCmd;
import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.models.Categoria;
import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.models.Ingrediente;
import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.models.IngredientesReceta;
import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.models.Receta;
import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.respositories.IngredienteRecetaRepository;
import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.respositories.IngredienteRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
@RequiredArgsConstructor
public class IngredienteRecetaService {

    private final IngredienteRecetaRepository ingredienteRecetaRepository;
    private final IngredienteService ingredienteService;
    private final RecetaService recetaService;


    public IngredientesReceta save(IngredienteRecetaCmd cmd){

        Ingrediente ingrediente = ingredienteService.getById(cmd.idIngrediente());
        Receta receta = recetaService.getById(cmd.idReceta());

        return IngredientesReceta.builder()
                .cantidad(cmd.cantidad())
                .ingrediente(ingrediente)
                .receta(receta)
                .unidad(cmd.unidad())
                .build();
    }


}
