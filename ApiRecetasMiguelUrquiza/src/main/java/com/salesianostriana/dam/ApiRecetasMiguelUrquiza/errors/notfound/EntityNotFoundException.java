package com.salesianostriana.dam.ApiRecetasMiguelUrquiza.errors.notfound;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String message) {
        super(message);
    }

    public EntityNotFoundException(Long id){
        super("No se ha encontrado una entidad con el id: %d".formatted(id));
    }

    public EntityNotFoundException(){
        super("No se ha encontrado la entidad");
    }

}
