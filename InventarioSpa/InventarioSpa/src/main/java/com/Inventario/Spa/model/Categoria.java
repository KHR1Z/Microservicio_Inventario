package com.Inventario.Spa.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table( name= "categoria")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Categoria {
    @Id 
    private String idCategoria;

    @Column(nullable = false, length = 50)
    private String descripcion;

}
