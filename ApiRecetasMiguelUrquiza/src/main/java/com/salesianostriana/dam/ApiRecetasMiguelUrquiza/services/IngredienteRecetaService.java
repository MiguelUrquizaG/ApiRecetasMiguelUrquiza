package com.salesianostriana.dam.ApiRecetasMiguelUrquiza.services;

import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.dtos.IngredienteRecetaCmd;
import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.models.IngredientesReceta;
import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.respositories.IngredienteRecetaRepository;
import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.respositories.IngredienteRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
@RequiredArgsConstructor
public class IngredienteRecetaService {

    private final IngredienteRecetaRepository ingredienteRecetaRepository;


    public IngredientesReceta save(IngredienteRecetaCmd cmd){
        return ingredienteRecetaRepository.save(cmd.toEntity(cmd));
    }


}
