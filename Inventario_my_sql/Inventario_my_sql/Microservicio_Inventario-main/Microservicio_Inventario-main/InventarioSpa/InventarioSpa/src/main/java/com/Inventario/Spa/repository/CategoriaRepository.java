package com.Inventario.Spa.repository;

import com.Inventario.Spa.model.Categoria;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface CategoriaRepository extends JpaRepository<Categoria, String> {

    List<Categoria> findAll(); // Método para obtener todas las categorías
}
