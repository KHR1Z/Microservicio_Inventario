package com.Inventario.Spa.controller;

/*import com.Inventario.Spa.model.Categoria;*/
import com.Inventario.Spa.model.Producto;
import com.Inventario.Spa.service.ProductoService;
//Importar service//

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
/*import org.springframework.web.bind.annotation.RequestParam;*/
import org.springframework.web.bind.annotation.*;

import java.util.List;




@RestController

@RequestMapping("api/v1/inventario")

public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public ResponseEntity<List<Producto>> listar() {
        List<Producto> productos  = productoService.findAll();
        if (productos.isEmpty()) {
            return ResponseEntity.noContent().build();
            
        }
        return ResponseEntity.ok(productos);
        
    }

    @PostMapping
    public ResponseEntity<Producto> guardar(@RequestBody Producto producto) {
        Producto productoMuevo = productoService.save(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(productoMuevo);
    //    return new ResponseEntity<>(productoMuevo, HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> buscar(@PathVariable Integer id) {
        try {
            Producto producto = productoService.findById(id);
            return ResponseEntity.ok(producto);
        } catch ( Exception e ) {
            return  ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizar(@PathVariable Integer id, @RequestBody Producto producto) {
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
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            productoService.delete(id);
            return ResponseEntity.noContent().build();
        } catch ( Exception e ) {
            return  ResponseEntity.notFound().build();
        }
    }


    @GetMapping("categoria/{idCategoria}")
    public ResponseEntity<List<Producto>> listadoPorCategoria(@PathVariable String idCategoria) {
        List<Producto> listaProductos = productoService.findByCategoria(idCategoria);
        if(listaProductos.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(listaProductos);
    }
    
}
