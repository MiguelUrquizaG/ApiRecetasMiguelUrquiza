package com.salesianostriana.dam.ApiRecetasMiguelUrquiza.services;

import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.dtos.ingrediente.EditIngredienteCmd;
import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.errors.notfound.IngredienteNotFoundException;
import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.models.Ingrediente;
import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.respositories.IngredienteRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IngredienteService {

    private final IngredienteRespository ingredienteRespository;


    public List<Ingrediente> getAll(){
        List<Ingrediente> list = ingredienteRespository.findAll();

        if(list.isEmpty()){
            throw new IngredienteNotFoundException();
        }

        return list;
    }

    public Ingrediente getById(Long id){
        return ingredienteRespository.findById(id).orElseThrow(() -> new IngredienteNotFoundException(id));
    }

    public Ingrediente save(EditIngredienteCmd cmd){
        return ingredienteRespository.save(cmd.toEntity(cmd));
    }

    public Ingrediente edit (EditIngredienteCmd cmd, Long id){
        return ingredienteRespository.findById(id)
                .map(ingrediente -> {
                    ingrediente.setNombre(cmd.nombre());
                    return ingrediente;
                }).orElseThrow(() -> new IngredienteNotFoundException(id));
    }

    public void deleteById(Long id){
        Ingrediente ingrediente = ingredienteRespository.findById(id).orElseThrow(() -> new IngredienteNotFoundException(id));

        ingredienteRespository.deleteById(id);

    }

}
