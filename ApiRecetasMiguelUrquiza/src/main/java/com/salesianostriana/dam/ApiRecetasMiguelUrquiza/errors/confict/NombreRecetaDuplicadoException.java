package com.salesianostriana.dam.ApiRecetasMiguelUrquiza.errors.confict;

public class NombreRecetaDuplicadoException extends EntityConflict {
    public NombreRecetaDuplicadoException(String message) {
        super(message);
    }

    public NombreRecetaDuplicadoException(){
        super("Ya existe una receta con ese nombre.");
    }

}
