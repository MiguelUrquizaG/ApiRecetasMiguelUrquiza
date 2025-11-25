package com.salesianostriana.dam.ApiRecetasMiguelUrquiza.respositories;

import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.models.Receta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecetaRepository extends JpaRepository<Receta,Long> {
}
