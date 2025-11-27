package com.salesianostriana.dam.ApiRecetasMiguelUrquiza.errors.confict;

public class NombreCategoriaDuplicadoException extends EntityConflict {
    public NombreCategoriaDuplicadoException(String message) {
        super(message);
    }

    public NombreCategoriaDuplicadoException(){
        super("Ya existe una categoria con ese nombre.");
    }

}
