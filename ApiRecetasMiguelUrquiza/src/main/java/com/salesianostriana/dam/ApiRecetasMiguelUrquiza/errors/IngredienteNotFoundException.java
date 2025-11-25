package com.salesianostriana.dam.ApiRecetasMiguelUrquiza.errors;

public class IngredienteNotFoundException extends RuntimeException {
    public IngredienteNotFoundException(String message) {
        super(message);
    }

    public IngredienteNotFoundException(Long id){
        super("No se ha encontrado un ingrediente con el id: %d".formatted(id));
    }

    public IngredienteNotFoundException(){
        super("No se ha encontrado un ingrediente.");
    }

}
