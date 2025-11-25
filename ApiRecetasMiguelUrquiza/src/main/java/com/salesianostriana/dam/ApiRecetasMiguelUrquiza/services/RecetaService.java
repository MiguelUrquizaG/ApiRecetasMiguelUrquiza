package com.salesianostriana.dam.ApiRecetasMiguelUrquiza.services;

import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.dtos.EditRecetaCmd;
import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.errors.CategoriaNotFoundException;
import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.errors.RecetaNotFoundException;
import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.models.Receta;
import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.respositories.RecetaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Service
@RequiredArgsConstructor
@RequestMapping("/receta")
public class RecetaService {

    private final RecetaRepository recetaRepository;
    private final CategoriaService categoriaService;



    public List<Receta> getAll(){
        List<Receta> list = recetaRepository.findAll();

        if(list.isEmpty()){
            throw new RecetaNotFoundException();
        }

        return  list;

    }

    public Receta getById(Long id){
        return recetaRepository.findById(id).orElseThrow(() -> new CategoriaNotFoundException(id));
    }

    public Receta save (EditRecetaCmd cmd){

        if(categoriaService.getById(cmd.categoria().getId()) == null){
            throw new CategoriaNotFoundException("No existe la categoría en la que se desea guardar la receta");
        }

        return recetaRepository.save(cmd.toEntity(cmd));
    }

    public Receta edit (EditRecetaCmd cmd ,Long id){
        return recetaRepository.findById(id)
                .map(receta -> {
                    receta.setIngredientesRecetas(cmd.ingredientesRecetas());
                    receta.setCategoria(cmd.categoria());
                    receta.setDificultad(cmd.dificultad());
                    receta.setTiempoPreparacionMin(cmd.tiempoPreparacionMin());
                    receta.setNombre(cmd.nombre());
                    return receta;
                }).orElseThrow(() -> new RecetaNotFoundException(id));
    }

    public void deleteById(Long id){
        Receta receta = recetaRepository.findById(id).orElseThrow(() -> new RecetaNotFoundException("No se ha encontrado la receta que desea eliminar"));
    }

}
