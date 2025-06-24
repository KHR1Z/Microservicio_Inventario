package com.Inventario.Spa;

import com.Inventario.Spa.model.*;
import com.Inventario.Spa.repository.*;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

@Profile("dev")
@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public void run(String...args) throws Exception{
        Faker faker = new Faker();

        for (int i = 0; i < 100; i++) {
            Producto producto = new Producto();

            producto.setNombre(faker.commerce().productName());
            producto.setDescripcion(faker.lorem().sentence());
            producto.setPrecio(Double.parseDouble(faker.commerce().price()));
            productoRepository.save(producto);

            String idCategoria = "CAT" + (i % 10 + 1);
            Categoria categoria = new Categoria();
            categoria.setIdCategoria(idCategoria);
            
            producto.setCategoria(categoria);
            
            productoRepository.save(producto);// Genera un ID de categoría entre CAT1 y CAT10
        }   
    }
}
