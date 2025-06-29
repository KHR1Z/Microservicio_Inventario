package com.Inventario.Spa.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.Inventario.Spa.controller.CategoriaControllerV2;
import com.Inventario.Spa.model.Categoria;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class CategoriaModelAssembler implements RepresentationModelAssembler<Categoria, EntityModel<Categoria>> {

 @Override
 public EntityModel<Categoria> toModel(Categoria categoria) {
  return EntityModel.of(categoria,
    linkTo(methodOn(CategoriaControllerV2.class).findById(categoria.getIdCategoria())).withSelfRel(),
    linkTo(methodOn(CategoriaControllerV2.class).getAllCategorias()).withRel("categorias"));
  }
}

