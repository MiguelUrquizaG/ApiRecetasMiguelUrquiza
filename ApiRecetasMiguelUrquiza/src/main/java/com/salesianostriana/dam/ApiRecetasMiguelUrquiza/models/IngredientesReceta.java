package com.salesianostriana.dam.ApiRecetasMiguelUrquiza.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IngredientesReceta {

    @Id
    @GeneratedValue
    private Long id;
    //Los atributos serían Cantidad y Unidad. Para ID utilizo el id de cada uno de estos || Uso un ID autogenerado.
    private int cantidad;
    //private

}
