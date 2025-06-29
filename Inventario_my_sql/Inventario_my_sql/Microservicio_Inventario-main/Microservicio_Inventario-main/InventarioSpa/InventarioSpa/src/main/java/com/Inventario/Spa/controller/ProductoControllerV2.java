package com.Inventario.Spa.controller;

import com.Inventario.Spa.assemblers.ProductoModelAssembler;
import com.Inventario.Spa.model.Producto;
import com.Inventario.Spa.service.ProductoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api/v2/productos")
public class ProductoControllerV2 {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private ProductoModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Producto>> getAllProductos() {
        List<Producto> productos = productoService.findAll();
        List<EntityModel<Producto>> modeloproductos = productos.stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(modeloproductos,
                linkTo(methodOn(ProductoControllerV2.class).getAllProductos()).withSelfRel());
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Producto>> findById(@PathVariable Integer id) {
        Producto producto = productoService.findById(id);
        return ResponseEntity.ok(assembler.toModel(producto));
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Producto>> save(@RequestBody Producto producto) {
        Producto nuevoProducto = productoService.save(producto);
        return ResponseEntity
                .created(linkTo(methodOn(ProductoControllerV2.class)
                .findById(nuevoProducto.getIdProducto())).toUri())
                .body(assembler.toModel(nuevoProducto));
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Producto>> update(@RequestBody Producto producto) {
        Producto updateProducto = productoService.save(producto);
        return ResponseEntity
            .ok(assembler.toModel(updateProducto));
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<?> delete(@PathVariable long id) {
        productoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/categoria/{idCategoria}", produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Producto>> findByCategoria(@PathVariable String idCategoria) {
        List<Producto> productos = productoService.findByCategoria(idCategoria);
        List<EntityModel<Producto>> modeloproductos = productos.stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(modeloproductos,
                linkTo(methodOn(ProductoControllerV2.class).findByCategoria(idCategoria)).withSelfRel());
    }



}
