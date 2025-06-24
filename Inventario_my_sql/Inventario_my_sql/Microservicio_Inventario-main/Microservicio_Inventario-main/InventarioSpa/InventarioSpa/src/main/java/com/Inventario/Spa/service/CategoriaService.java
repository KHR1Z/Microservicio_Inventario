package com.Inventario.Spa.service;

import com.Inventario.Spa.model.Categoria;
import com.Inventario.Spa.repository.CategoriaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional

public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> getAllCategorias() {
        return categoriaRepository.findAll();
    }

    public Categoria findById(String id) {
        return categoriaRepository.findById(id).get();
    }

    public Categoria save(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }
    
    public void delete(String id) {
        categoriaRepository.deleteById(id);
    }
    
}
