package com.salesianostriana.dam.ApiRecetasMiguelUrquiza.errors;

public class RecetaNotFoundException extends RuntimeException {
    public RecetaNotFoundException(String message) {
        super(message);
    }

    public RecetaNotFoundException(Long id){
        super("No se ha encontrado receta con el id: %d".formatted(id));
    }
    public RecetaNotFoundException(){
        super("No se ha encontrado la receta buscada");
    }

}
