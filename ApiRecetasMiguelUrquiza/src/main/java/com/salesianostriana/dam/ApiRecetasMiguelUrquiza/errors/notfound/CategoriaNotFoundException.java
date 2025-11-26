package com.salesianostriana.dam.ApiRecetasMiguelUrquiza.errors.notfound;



public class CategoriaNotFoundException extends EntityNotFoundException {
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
