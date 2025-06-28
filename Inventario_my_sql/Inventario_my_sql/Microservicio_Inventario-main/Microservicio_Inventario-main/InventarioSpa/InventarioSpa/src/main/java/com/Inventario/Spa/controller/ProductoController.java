package com.Inventario.Spa.controller;

/*import com.Inventario.Spa.model.Categoria;*/
import com.Inventario.Spa.model.Producto;
import com.Inventario.Spa.service.ProductoService;
//Importar service//

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
/*import org.springframework.web.bind.annotation.RequestParam;*/
import org.springframework.web.bind.annotation.*;

import java.util.List;




@RestController

@RequestMapping("api/v1/inventario")

@Tag(name = "Productos", description = "Operaciones relacionadas con los productos del inventario")

public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    @Operation(summary = "Listar todos los productos", description = "Obtiene una lista de todos los productos")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Productos encontrados",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Producto.class))),
        @ApiResponse(responseCode = "404", description = "No se encontraron productos")
    })
    public ResponseEntity<List<Producto>> listar() {
        List<Producto> productos  = productoService.findAll();
        if (productos.isEmpty()) {
            return ResponseEntity.noContent().build();
            
        }
        return ResponseEntity.ok(productos);
        
    }

    @PostMapping
    @Operation(summary = "Guardar un nuevo producto", description = "Crea un nuevo producto en el inventario")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Producto creado correctamente",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Producto.class))),
        @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    })
    public ResponseEntity<Producto> guardar(@RequestBody Producto producto) {
        Producto productoMuevo = productoService.save(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(productoMuevo);
    //    return new ResponseEntity<>(productoMuevo, HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar producto por ID", description = "Obtiene un producto específico por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Producto encontrado",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Producto.class))),
        @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    public ResponseEntity<Producto> buscar(
        @Parameter(description = "ID del producto a buscar", required = true, example = "1")
        @PathVariable Integer id) {
        try {
            Producto producto = productoService.findById(id);
            return ResponseEntity.ok(producto);
        } catch ( Exception e ) {
            return  ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar producto", description = "Actualiza los detalles de un producto existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Producto actualizado correctamente",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Producto.class))),
        @ApiResponse(responseCode = "404", description = "Producto no encontrado"),
        @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    })
    public ResponseEntity<Producto> actualizar(
        @Parameter(description = "ID del producto a actualizar", required = true, example = "1")
        @PathVariable Integer id, @RequestBody Producto producto) {
        try {
            Producto prod = productoService.findById(id);
            prod.setIdProducto(id);
            prod.setNombre(producto.getNombre());
            prod.setDescripcion(producto.getDescripcion());
            prod.setPrecio(producto.getPrecio());
            prod.setStock(producto.getStock());
            prod.setCategoria(producto.getCategoria());

            productoService.save(prod);
            return ResponseEntity.ok(producto);
        } catch ( Exception e ) {
            return  ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar producto", description = "Elimina un producto del inventario por su ID")
    @ApiResponse(responseCode = "204", description = "Categoría eliminada correctamente")
    @ApiResponse(responseCode = "404", description = "Categoría no encontrada")
    public ResponseEntity<?> eliminar(
        @Parameter(description = "ID del producto a eliminar", required = true, example = "1")
        @PathVariable Long id) {
        try {
            productoService.delete(id);
            return ResponseEntity.noContent().build();
        } catch ( Exception e ) {
            return  ResponseEntity.notFound().build();
        }
    }


    @GetMapping("categoria/{idCategoria}")
    @Operation(summary = "Listar productos por categoría", description = "Obtiene una lista de productos filtrados por categoría")
    public ResponseEntity<List<Producto>> listadoPorCategoria(
        @Parameter(description = "ID de la categoría para filtrar productos", required = true, example = "CAT001")
        @PathVariable String idCategoria) {
        List<Producto> listaProductos = productoService.findByCategoria(idCategoria);
        if(listaProductos.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(listaProductos);
    }
    
}
