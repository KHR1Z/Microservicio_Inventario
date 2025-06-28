package com.Inventario.Spa;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import com.Inventario.Spa.model.Categoria;
import com.Inventario.Spa.service.CategoriaService;
import com.Inventario.Spa.controller.CategoriaController;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

@WebMvcTest(CategoriaController.class)
public class CategoriaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoriaService categoriaService;

    @Autowired
    private ObjectMapper objectMapper;

    private Categoria categoria;

    @BeforeEach
    void setUp(){
        categoria = new Categoria();
        categoria.setIdCategoria("CAT002");
        categoria.setDescripcion("Categoria de prueba");
    }
    
    @Test
    public void testGetAllCategorias() throws Exception {
        when(categoriaService.getAllCategorias()).thenReturn(List.of(categoria));

        mockMvc.perform(get("/api/v1/categorias"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].idCategoria").value("CAT002"))
                .andExpect(jsonPath("$[0].descripcion").value("Categoria de prueba"));
    }

    @Test
    public void testGetCategoriaById() throws Exception {
        when(categoriaService.findById("CAT002")).thenReturn(categoria);

        mockMvc.perform(get("/api/v1/categorias/CAT002"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idCategoria").value("CAT002"))
                .andExpect(jsonPath("$.descripcion").value("Categoria de prueba"));
    }

    @Test
    public void testCreateCategoria() throws Exception {
        when(categoriaService.save(any(Categoria.class))).thenReturn(categoria);

        mockMvc.perform(post("/api/v1/categorias")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(categoria)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.idCategoria").value("CAT002"))
            .andExpect(jsonPath("$.descripcion").value("Categoria de prueba"));
    }

    @Test
    public void testUpdateCategoria() throws Exception {
        when(categoriaService.findById("CAT002")).thenReturn(categoria);
        when(categoriaService.save(any(Categoria.class))).thenReturn(categoria);

        mockMvc.perform(put("/api/v1/categorias/CAT002")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(categoria)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.idCategoria").value("CAT002"))
            .andExpect(jsonPath("$.descripcion").value("Categoria de prueba"));
    
    }

    @Test
    public void testDeleteCategoria() throws Exception {
        when(categoriaService.findById("CAT002")).thenReturn(categoria);
        doNothing().when(categoriaService).delete("CAT002"); 

        mockMvc.perform(delete("/api/v1/categorias/CAT002"))
                .andExpect(status().isNoContent());

        verify(categoriaService, times(1)).delete("CAT002");
    }


}
