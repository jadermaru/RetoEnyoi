package com.reto.reto.aplication.Services;

import com.reto.reto.aplication.dtos.ProductosDTO;
import com.reto.reto.aplication.entities.Productos;
import com.reto.reto.aplication.payloads.requests.CrearProductosRequest;
import com.reto.reto.aplication.payloads.requests.ProductosRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProductosService {
    List<ProductosDTO> findAll();

    ProductosDTO findById(Long id);

    ProductosDTO findByNombreProducto(String nombreProducto);

    ProductosDTO findByEstado(String estado);

    ProductosDTO create(CrearProductosRequest crearProductosRequest);

    ProductosDTO update(ProductosRequest productosRequest);

    String delete(Long id);
}
