package com.salesianostriana.dam.ApiRecetasMiguelUrquiza.errors.confict;

public class CategoriaRecetaConflict extends EntityConflict {
    public CategoriaRecetaConflict(String message) {
        super(message);
    }

    public CategoriaRecetaConflict(){
        super("Conflicto con las recetas y las categorías.");
    }

}
