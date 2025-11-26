package com.salesianostriana.dam.ApiRecetasMiguelUrquiza.errors.confict;

public class EntityConflict extends RuntimeException {
    public EntityConflict(String message) {
        super(message);
    }

    public EntityConflict (){
        super("Ya existe una entidad con ese nombre");
    }

}
