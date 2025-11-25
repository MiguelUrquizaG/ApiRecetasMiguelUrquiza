package com.salesianostriana.dam.ApiRecetasMiguelUrquiza.respositories;

import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.models.Ingrediente;
import com.salesianostriana.dam.ApiRecetasMiguelUrquiza.models.IngredientesReceta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredienteRecetaRepository extends JpaRepository<IngredientesReceta,Long> {
}
