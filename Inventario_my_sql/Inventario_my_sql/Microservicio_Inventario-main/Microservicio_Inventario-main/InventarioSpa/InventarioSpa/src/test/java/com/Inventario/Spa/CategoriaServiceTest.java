package com.Inventario.Spa;


import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.Inventario.Spa.model.Categoria;
import com.Inventario.Spa.repository.CategoriaRepository;
import com.Inventario.Spa.service.CategoriaService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class CategoriaServiceTest {

    @Autowired
    private CategoriaService categoriaService;

    @MockBean
    private CategoriaRepository categoriaRepository;

    @Test
    public void testGetAllCategorias() {
        when (categoriaRepository.findAll()).thenReturn(List.of(
                new Categoria("CAT43", "Categoria facial"),
                new Categoria("CC22", "Categoria cuidado capilar")
        ));

        List<Categoria> listaCategorias = categoriaService.getAllCategorias();

        assertNotNull(listaCategorias);
        assertEquals(2, listaCategorias.size());
    }

    @Test
    public void testFindById() {
        Categoria categoria = new Categoria("CAT43", "Categoria facial");

        when(categoriaRepository.findById("CAT43")).thenReturn(Optional.of(categoria));

        Categoria found = categoriaService.findById("CAT43");

        assertNotNull(found);
        assertEquals("CAT43", found.getIdCategoria());

    }

    @Test
    public void testSave() {
        Categoria categoria = new Categoria("CAT43", "Categoria facial");

        when(categoriaRepository.save(categoria)).thenReturn(categoria);

        Categoria saved = categoriaService.save(categoria);

        assertNotNull(saved);
        assertEquals("CAT43", saved.getIdCategoria());
    }

    @Test 
    public void testdelete(){

        String id = "CAT43";
        doNothing().when(categoriaRepository).deleteById(id);
        categoriaService.delete(id);

        verify(categoriaRepository, times(1)).deleteById(id);
    }
}