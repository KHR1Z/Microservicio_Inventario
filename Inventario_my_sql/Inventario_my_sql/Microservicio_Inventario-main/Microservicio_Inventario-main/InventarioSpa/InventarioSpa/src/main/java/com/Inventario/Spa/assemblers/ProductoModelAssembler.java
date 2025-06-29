package com.Inventario.Spa.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.Inventario.Spa.controller.ProductoControllerV2;
import com.Inventario.Spa.model.Producto;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;


@Component
public class ProductoModelAssembler implements RepresentationModelAssembler<Producto, EntityModel<Producto>> {

 @Override
 public EntityModel<Producto> toModel(Producto producto) {
  return EntityModel.of(producto,
    linkTo(methodOn(ProductoControllerV2.class).findById(producto.getIdProducto())).withSelfRel(),
    linkTo(methodOn(ProductoControllerV2.class).getAllProductos()).withRel("productos"));
  }
}

