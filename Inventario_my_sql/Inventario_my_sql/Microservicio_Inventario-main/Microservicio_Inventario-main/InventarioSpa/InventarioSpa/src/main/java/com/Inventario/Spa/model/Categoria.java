package com.Inventario.Spa.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table( name= "categoria")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Modelo de la Categoria de un producto")

public class Categoria {
    @Id 
    @Schema(description = "ID de la categoría", example = "CAT001")
    private String idCategoria;

    @Column(nullable = false, length = 50)
    @Schema(description = "Descripción de la categoría", example = "Productos de Belleza")
    private String descripcion;

}
