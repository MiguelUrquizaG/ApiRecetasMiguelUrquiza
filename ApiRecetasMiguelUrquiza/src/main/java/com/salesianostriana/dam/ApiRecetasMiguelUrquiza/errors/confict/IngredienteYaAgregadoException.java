package com.salesianostriana.dam.ApiRecetasMiguelUrquiza.errors.confict;

public class IngredienteYaAgregadoException extends EntityConflict {
    public IngredienteYaAgregadoException(String message) {
        super(message);
    }

    public IngredienteYaAgregadoException(){
        super("Este ingrediente ya esta agregado en la receta.");
    }

}
