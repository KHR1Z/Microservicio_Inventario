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
    public void run(String... args) throws Exception {
        Faker faker = new Faker();
        Random random = new Random();


        // Generar categorías
        for (int i = 1; i <= 5; i++){
            Categoria categoria = new Categoria();
            categoria.setIdCategoria("null");
            categoria.setDescripcion(faker.commerce().department());
            productoRepository.save(categoria);

        }

        // Generar productos
        for (int i = 1; i <= 50; i++) {
            Producto producto = new Producto();
            producto.setNombre(faker.commerce().productName());
            producto.setDescripcion(faker.lorem().sentence());
            producto.setPrecio(random.nextDouble() * 100 + 10);
            producto.setStock(random.nextInt(100) + 1); 

            productoRepository.save(producto);
        }

    }
}
