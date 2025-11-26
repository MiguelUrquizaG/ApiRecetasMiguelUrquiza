package com.salesianostriana.dam.ApiRecetasMiguelUrquiza.services;

import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.dtos.EditRecetaCmd;
import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.dtos.IngredienteRecetaCmd;
import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.errors.CategoriaNotFoundException;
import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.errors.RecetaNotFoundException;
import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.models.*;
import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.respositories.IngredienteRecetaRepository;
import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.respositories.RecetaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecetaService {

    private final RecetaRepository recetaRepository;
    private final CategoriaService categoriaService;
    private final IngredienteService ingredienteService;
    private final IngredienteRecetaService ingredienteRecetaService;



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

        Categoria categoria = categoriaService.getById(cmd.idCategoria());


        if(categoria == null){
            throw new CategoriaNotFoundException("No existe la categoría en la que se desea guardar la receta");
        }

        return Receta.builder()
                .nombre(cmd.nombre())
                .tiempoPreparacionMin(cmd.tiempoPreparacionMin())
                .dificultad(cmd.dificultad())
                .categoria(categoria)
                .build();
    }

    public Receta edit (EditRecetaCmd cmd ,Long id){

        Categoria c  = categoriaService.getById(cmd.idCategoria());

        return recetaRepository.findById(id)
                .map(receta -> {
                    receta.setCategoria(c);
                    receta.setDificultad(cmd.dificultad());
                    receta.setTiempoPreparacionMin(cmd.tiempoPreparacionMin());
                    receta.setNombre(cmd.nombre());
                    return receta;
                }).orElseThrow(() -> new RecetaNotFoundException(id));
    }

    public void deleteById(Long id){
        Receta receta = recetaRepository.findById(id).orElseThrow(() -> new RecetaNotFoundException("No se ha encontrado la receta que desea eliminar"));
    }

    /*
    public Receta addIngredienteToReceta(IngredienteRecetaCmd cmd){
        //Arreglar ids de busqueda

        Receta receta = recetaRepository.findById(idReceta).orElseThrow(() -> new RecetaNotFoundException(idReceta));
        Ingrediente ingrediente = ingredienteService.getById(idIngrediente);
        IngredienteRecetaCmd cmd  = new IngredienteRecetaCmd(cantidad,ingrediente.getId(),receta.getId(),tipoUnidad);
        ingredienteRecetaService.save(cmd);

        return receta;

    }*/

}
