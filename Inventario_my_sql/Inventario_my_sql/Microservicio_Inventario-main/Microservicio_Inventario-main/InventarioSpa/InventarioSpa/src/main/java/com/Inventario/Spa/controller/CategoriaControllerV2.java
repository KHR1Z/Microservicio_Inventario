package com.Inventario.Spa.controller;

import com.Inventario.Spa.assemblers.CategoriaModelAssembler;
import com.Inventario.Spa.model.Categoria;
import com.Inventario.Spa.service.CategoriaService;

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
@RequestMapping("/api/v2/categorias")
public class CategoriaControllerV2 {

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private CategoriaModelAssembler assembler;


    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Categoria>> getAllCategorias() {
        List<Categoria> categorias = categoriaService.getAllCategorias();
        List<EntityModel<Categoria>> modelocategorias = categorias.stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(modelocategorias,
                linkTo(methodOn(CategoriaControllerV2.class).getAllCategorias()).withSelfRel());
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<Categoria> findById(@PathVariable String id) {
        Categoria categoria = categoriaService.findById(id);
        return assembler.toModel(categoria);
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Categoria>> save(@RequestBody Categoria categoria) {
        Categoria nuevaCategoria = categoriaService.save(categoria);
        return ResponseEntity
                .created(linkTo(methodOn(CategoriaControllerV2.class)
                .findById(nuevaCategoria.getIdCategoria())).toUri())
                .body(assembler.toModel(nuevaCategoria));
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Categoria>> updateCategoria(@PathVariable String id, @RequestBody Categoria categoria) {
        categoria.setIdCategoria(id);
        Categoria updateCategoria = categoriaService.save(categoria);
        return ResponseEntity
                .ok(assembler.toModel(updateCategoria));
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<?> deleteCategoria(@PathVariable String id) {
        categoriaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    
}