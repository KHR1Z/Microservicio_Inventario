package com.Inventario.Spa.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table( name= "producto")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Modelo del Producto en el inventario del Spa")

public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID del producto", example = "1")
    private Integer idProducto; 

    @Column(nullable=false, length = 50)
    @Schema(description = "Nombre del producto", example = "Aceite Esencial")
    private String nombre;

    @Column(nullable = false, length = 100)
    @Schema(description = "Descripción del producto", example = "Aceite esencial de lavanda para aromaterapia")
    private String Descripcion; 

    @Column(nullable = false)
    @Schema(description = "Precio del producto", example = "19.99")
    private double Precio;

    @Column(nullable = false,columnDefinition = "INT (4)" )
    @Schema(description = "Cantidad en stock del producto", example = "100")
    private int stock;

    @ManyToOne
    @JoinColumn(name = "id_categoria",referencedColumnName = "idCategoria", nullable = false)
    @Schema(description = "Categoría a la que pertenece el producto")
    private Categoria categoria;

}