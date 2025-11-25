package com.salesianostriana.dam.ApiRecetasMiguelUrquiza.respositories;

import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.models.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria,Long> {
}
