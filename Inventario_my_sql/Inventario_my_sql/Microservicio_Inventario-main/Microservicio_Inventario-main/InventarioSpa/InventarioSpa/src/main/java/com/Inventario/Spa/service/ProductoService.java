package com.Inventario.Spa.service;

/*import com.Inventario.Spa.model.Categoria;*/
import com.Inventario.Spa.model.Producto;
import com.Inventario.Spa.repository.ProductoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional

public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> findAll() {
        return productoRepository.findAll();
    }   

    public Producto findById(long id) {
        return productoRepository.findById(id).get();
    }

    public Producto save(Producto producto) {
        return productoRepository.save(producto);
    }

    public void delete(Long id) {
        productoRepository.deleteById(id);
    }

    public List<Producto> findByCategoria(String idCategoria) {
        return productoRepository.findByCategoria_IdCategoria(idCategoria);
    }
}
