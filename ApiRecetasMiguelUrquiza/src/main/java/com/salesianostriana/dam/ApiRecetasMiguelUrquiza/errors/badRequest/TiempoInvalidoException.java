package com.salesianostriana.dam.ApiRecetasMiguelUrquiza.errors.badRequest;

public class TiempoInvalidoException extends EntityBadRequestException {
    public TiempoInvalidoException(String message) {
        super(message);
    }

    public TiempoInvalidoException(){
        super("El tiempo de preparación no puede ser menor o igual que cero.");
    }
}
