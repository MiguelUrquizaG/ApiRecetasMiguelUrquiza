package com.salesianostriana.dam.ApiRecetasMiguelUrquiza.errors;



public class CategoriaNotFoundException extends RuntimeException {
    public CategoriaNotFoundException(String message) {
        super(message);
    }

    public CategoriaNotFoundException(Long id){
        super("No se ha encontrado una categoría con el id: %d".formatted(id));
    }

    public CategoriaNotFoundException(){
        super("No se ha encontrado la categoría");
    }

}
