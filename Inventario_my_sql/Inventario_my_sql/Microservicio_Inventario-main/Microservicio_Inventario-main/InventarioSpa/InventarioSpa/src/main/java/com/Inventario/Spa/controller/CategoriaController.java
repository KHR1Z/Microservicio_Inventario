package com.Inventario.Spa.controller;

import com.Inventario.Spa.model.Categoria;
import com.Inventario.Spa.service.CategoriaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController

@RequestMapping("api/v1/categorias")

@Tag(name = "Categorias", description = "Operaciones relacionadas con las categorías")

public class CategoriaController {
    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    @Operation(summary = "Listar todas las categorías", description = "Obtiene una lista de todas las categorías disponibles")
    public ResponseEntity<List<Categoria>> listar() {
        List<Categoria> listaCategorias = categoriaService.getAllCategorias();
        if (listaCategorias.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(listaCategorias);
    }

    @PostMapping
    @Operation(summary = "Guardar una nueva categoría", description = "Crea una nueva categoría en el sistema")
    public ResponseEntity<Categoria> guardar(@RequestBody Categoria categoria) {
        Categoria categoriaNueva = categoriaService.save(categoria);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaNueva);
    }


    @GetMapping("/{id}")
    @Operation(summary = "Buscar categoría por ID", description = "Obtiene una categoría específica por su ID")
    public ResponseEntity<Categoria> buscar(@PathVariable String id) {
        try {
            Categoria categoria = categoriaService.findById(id);
            return ResponseEntity.ok(categoria);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar categoría", description = "Actualiza los detalles de una categoría existente")
    public ResponseEntity<Categoria> actualizar(@PathVariable String id, @RequestBody Categoria categoria) {
        try {
            Categoria cat = categoriaService.findById(id);
            cat.setIdCategoria(id);
            cat.setDescripcion(categoria.getDescripcion());
            Categoria categoriaActualizada = categoriaService.save(cat);
            return ResponseEntity.ok(categoriaActualizada);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar categoría", description = "Elimina una categoría del sistema por su ID")
    public ResponseEntity<Void> eliminar(@PathVariable String id) {
        try {
            categoriaService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }


}
