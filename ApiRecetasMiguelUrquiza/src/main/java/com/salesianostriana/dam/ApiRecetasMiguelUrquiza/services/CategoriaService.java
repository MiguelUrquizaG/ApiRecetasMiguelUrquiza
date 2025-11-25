package com.salesianostriana.dam.ApiRecetasMiguelUrquiza.services;

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
          throw null;
      }

      return list;

    }



}
