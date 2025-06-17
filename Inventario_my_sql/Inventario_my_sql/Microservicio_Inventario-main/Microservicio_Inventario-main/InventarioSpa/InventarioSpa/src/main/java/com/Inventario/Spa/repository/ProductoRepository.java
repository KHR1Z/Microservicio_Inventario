package com.Inventario.Spa.repository;

/*import com.Inventario.Spa.model.Categoria; */
import com.Inventario.Spa.model.Producto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository

public interface ProductoRepository extends JpaRepository<Producto, Long> {

    List <Producto> findByCategoria_IdCategoria(String idCategoria);

}
