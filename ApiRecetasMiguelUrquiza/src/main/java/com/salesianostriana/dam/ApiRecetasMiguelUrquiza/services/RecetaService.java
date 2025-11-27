package com.salesianostriana.dam.ApiRecetasMiguelUrquiza.services;

import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.dtos.receta.EditRecetaCmd;
import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.dtos.ingrediente_receta.IngredienteRecetaCmd;
import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.errors.badRequest.TiempoInvalidoException;
import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.errors.confict.CategoriaRecetaConflict;
import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.errors.confict.IngredienteYaAgregadoException;
import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.errors.confict.NombreRecetaDuplicadoException;
import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.errors.notfound.CategoriaNotFoundException;
import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.errors.notfound.RecetaNotFoundException;
import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.models.*;
import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.respositories.IngredienteRecetaRepository;
import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.respositories.RecetaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class RecetaService {

    private final RecetaRepository recetaRepository;
    private final CategoriaService categoriaService;
    private final IngredienteService ingredienteService;
    @Lazy
    private final IngredienteRecetaRepository ingredienteRecetaRepository;


    public List<Receta> getAll() {
        List<Receta> list = recetaRepository.findAll();

        if (list.isEmpty()) {
            throw new RecetaNotFoundException();
        }

        return list;

    }

    public Receta getById(Long id) {
        return recetaRepository.findById(id).orElseThrow(() -> new CategoriaNotFoundException(id));
    }

    public Receta save(EditRecetaCmd cmd) {


        Categoria categoria;
        //Esto es un 400 y lo gestiono con un try catch
        try {
            categoria = categoriaService.getById(cmd.idCategoria());
        } catch (CategoriaNotFoundException ex) {
            throw new IllegalArgumentException("No funciona");
        }

        recetaRepository.findAll().forEach(receta -> {
            if (receta.getNombre().equalsIgnoreCase(cmd.nombre())){
                throw new NombreRecetaDuplicadoException();
            }
        });

        if(cmd.tiempoPreparacionMin()<=0){
            throw new TiempoInvalidoException();
        }


        return recetaRepository.save(Receta.builder()
                .nombre(cmd.nombre())
                .tiempoPreparacionMin(cmd.tiempoPreparacionMin())
                .dificultad(cmd.dificultad())
                .categoria(categoria)
                .build());
    }

    public Receta edit(EditRecetaCmd cmd, Long id) {

        Categoria c = categoriaService.getById(cmd.idCategoria());

        return recetaRepository.findById(id)
                .map(receta -> {
                    receta.setCategoria(c);
                    receta.setDificultad(cmd.dificultad());
                    receta.setTiempoPreparacionMin(cmd.tiempoPreparacionMin());
                    receta.setNombre(cmd.nombre());
                    return receta;
                }).orElseThrow(() -> new RecetaNotFoundException(id));
    }

    public void deleteById(Long id) {
        Receta receta = recetaRepository.findById(id).orElseThrow(() -> new RecetaNotFoundException("No se ha encontrado la receta que desea eliminar"));
        if(receta.getCategoria()!=null){
            throw new CategoriaRecetaConflict("No se puede eliminar una receta relacionada a una categoría");
        }
        recetaRepository.deleteById(id);
    }


    public Receta addIngredienteToReceta(IngredienteRecetaCmd cmd, Long idReceta) {

        Receta receta = recetaRepository.findById(idReceta).orElseThrow(() -> new RecetaNotFoundException(idReceta));
        Ingrediente ingrediente = ingredienteService.getById(cmd.idIngrediente());
        IngredienteRecetaCmd newCmd = new IngredienteRecetaCmd(cmd.cantidad(), ingrediente.getId(), idReceta, cmd.unidad());



        receta.getIngredientesRecetas().forEach(ingredientesReceta -> {
            if(Objects.equals(ingredientesReceta.getIngrediente().getId(), cmd.idIngrediente())){
                throw new IngredienteYaAgregadoException();
            }
        });

        IngredientesReceta response = ingredienteRecetaRepository.save(IngredientesReceta.builder()
                .receta(receta)
                .ingrediente(ingrediente)
                .unidad(cmd.unidad())
                .cantidad(cmd.cantidad())
                .build());


        receta.getIngredientesRecetas().add(response);

        return receta;

    }

}
