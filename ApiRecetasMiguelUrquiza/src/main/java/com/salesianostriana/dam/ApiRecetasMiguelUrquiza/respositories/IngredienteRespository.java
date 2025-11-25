package com.salesianostriana.dam.ApiRecetasMiguelUrquiza.respositories;

import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.models.Ingrediente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredienteRespository extends JpaRepository<Ingrediente,Long> {
}
