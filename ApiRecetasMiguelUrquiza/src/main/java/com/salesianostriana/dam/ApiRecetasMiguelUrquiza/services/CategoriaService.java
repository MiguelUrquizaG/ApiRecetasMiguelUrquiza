package com.salesianostriana.dam.ApiRecetasMiguelUrquiza.services;

import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.dtos.EditCategoriaCmd;
import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.errors.CategoriaNotFoundException;
import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.models.Categoria;
import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.respositories.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Service
@RequestMapping("/categoria")
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public List<Categoria> getAll(){
      List<Categoria> list = categoriaRepository.findAll();

      if(list.isEmpty()){
          throw new CategoriaNotFoundException();
      }

      return list;

    }

    public Categoria getById(Long id){
        return categoriaRepository.findById(id).orElseThrow(() -> new CategoriaNotFoundException(id));
    }

    public Categoria save(EditCategoriaCmd cmd){
        return categoriaRepository.save(cmd.toEntity(cmd));
    }

    public Categoria edit(EditCategoriaCmd cmd,Long id){
        return categoriaRepository.findById(id)
                .map(categoria -> {
                    categoria.setNombre(cmd.nombre());
                    categoria.setDescripcion(cmd.descripcion());
                    categoria.setListaRecetas(cmd.listaRecetas());
                    return categoriaRepository.save(categoria);
                }).orElseThrow(() -> new CategoriaNotFoundException(id));
    }


    public void deleteById(Long id){
        Categoria c = categoriaRepository.findById(id).orElseThrow(() -> new CategoriaNotFoundException("No se encuentra la categoría para eliminarla"));

        categoriaRepository.deleteById(id);
    }


}
