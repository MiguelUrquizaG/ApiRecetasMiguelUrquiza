package com.salesianostriana.dam.ApiRecetasMiguelUrquiza.models;

import jakarta.persistence.*;
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
    @Enumerated(EnumType.STRING)
    private TipoUnidad unidad;

    @ManyToOne
    private Ingrediente ingrediente;

    @ManyToOne
    private Receta receta;


}
