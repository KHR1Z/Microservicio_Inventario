package com.Inventario.Spa;

import com.Inventario.Spa.model.*;
import com.Inventario.Spa.repository.*;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;



@Profile("dev")
@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();

        // Crear categorías primero
        for (int i = 1; i <= 10; i++) {
            String idCategoria = "CAT" + i;
            Categoria categoria = new Categoria();
            categoria.setIdCategoria(idCategoria);
            categoria.setDescripcion("Descripción de la categoría " + i);
            categoriaRepository.save(categoria);
        }

        // Crear productos y asignar categorías
        for (int i = 0; i < 100; i++) {
            Producto producto = new Producto();

            producto.setNombre(faker.commerce().productName());
            producto.setDescripcion(faker.lorem().sentence());
            producto.setPrecio(Double.parseDouble(faker.commerce().price()));
            producto.setStock(faker.number().numberBetween(1, 100));

            // Asignar una categoría existente
            String idCategoria = "CAT" + (i % 10 + 1);
            Categoria categoria = categoriaRepository.findById(idCategoria)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada: " + idCategoria));
            producto.setCategoria(categoria);

            productoRepository.save(producto);
        }
    }
}
