package com.Inventario.Spa.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table( name= "producto")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProducto; 

    @Column(nullable=false, length = 50)
    private String nombre;

    @Column(nullable = false, length = 100)
    private String Descripcion; 

    @Column(nullable = false)
    private double Precio;

    @Column(nullable = false,columnDefinition = "NUMBER (4)" )
    private int stock;

    @ManyToOne
    @JoinColumn(name = "id_categoria",referencedColumnName = "idCategoria", nullable = false)
    private Categoria categoria;

}