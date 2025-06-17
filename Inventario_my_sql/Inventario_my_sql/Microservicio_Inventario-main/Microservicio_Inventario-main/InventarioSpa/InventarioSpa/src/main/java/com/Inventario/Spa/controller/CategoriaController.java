package com.Inventario.Spa.controller;

import com.Inventario.Spa.model.Categoria;
import com.Inventario.Spa.model.Producto;
import com.Inventario.Spa.service.CategoriaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/categorias")


public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @PostMapping
    public ResponseEntity<Categoria> guardar(@RequestBody  Categoria categoria) {
        Categoria categoriaNueva = categoriaService.save(categoria);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaNueva);
    //    return new ResponseEntity<>(productoMuevo, HttpStatus.ACCEPTED);
    }



}
