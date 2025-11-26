package com.salesianostriana.dam.ApiRecetasMiguelUrquiza.errors.confict;

public class NombreCategoriaDuplicadoException extends RuntimeException {
    public NombreCategoriaDuplicadoException(String message) {
        super(message);
    }

    public NombreCategoriaDuplicadoException(){
        super("El nombre ");
    }

}
