package com.salesianostriana.dam.ApiRecetasMiguelUrquiza.services;

import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.dtos.receta.EditRecetaCmd;
import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.dtos.ingrediente_receta.IngredienteRecetaCmd;
import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.errors.CategoriaNotFoundException;
import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.errors.RecetaNotFoundException;
import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.models.*;
import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.respositories.RecetaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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


    public Receta addIngredienteToReceta(IngredienteRecetaCmd cmd){

        Receta receta = recetaRepository.findById(cmd.idReceta()).orElseThrow(() -> new RecetaNotFoundException(cmd.idReceta()));
        Ingrediente ingrediente = ingredienteService.getById(cmd.idIngrediente());
        IngredienteRecetaCmd newCmd  = new IngredienteRecetaCmd(cmd.cantidad(),ingrediente.getId(),receta.getId(),cmd.unidad());
        ingredienteRecetaService.save(newCmd);

        return receta;

    }

}
