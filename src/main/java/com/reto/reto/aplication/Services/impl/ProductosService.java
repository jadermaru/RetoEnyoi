package com.reto.reto.aplication.Services.impl;

import com.reto.reto.aplication.Repositories.ProductosRepository;
import com.reto.reto.aplication.Services.IProductosService;
import com.reto.reto.aplication.dtos.ProductosDTO;
import com.reto.reto.aplication.entities.Productos;
import com.reto.reto.aplication.payloads.requests.CrearProductosRequest;
import com.reto.reto.aplication.payloads.requests.ProductosRequest;
import com.reto.reto.base.exceptions.AlreadyExists;
import com.reto.reto.base.exceptions.NotDataFound;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductosService implements IProductosService {
    //Utilidades
    private final ModelMapper modelMapper;
    private static final String NOEXISTENDATOS = "No existen datos";
    //Repositorios
    private final ProductosRepository productosRepository;

    @Override
    public List<ProductosDTO> findAll() {

            List<Productos> productosList = productosRepository.findAll();
            List<ProductosDTO> productosDTOList = new ArrayList<>();

        productosList.forEach(productos -> {
                ProductosDTO productosDTO = modelMapper.map(productos, ProductosDTO.class);
            productosDTOList.add(productosDTO);
            });

            return productosDTOList;
    }

    @Override
    public ProductosDTO findById(Long id) {
        Optional<Productos> productosOptional = productosRepository.findById(id);
        Productos productos;
        if (productosOptional.isPresent()) {
            productos = productosOptional.get();
            return modelMapper.map(productos, ProductosDTO.class);
        } else {
            throw new NotDataFound(NOEXISTENDATOS);
        }
    }

    @Override
    public ProductosDTO findByNombreProducto(String nombreProducto) {
        Optional<Productos> productosOptional = productosRepository.findByNombreProducto(nombreProducto);
        Productos productos;
        if (productosOptional.isPresent()) {
            productos = productosOptional.get();
            return modelMapper.map(productos, ProductosDTO.class);
        } else {
            throw new NotDataFound(NOEXISTENDATOS);
        }
    }

    @Override
    public ProductosDTO findByEstado(String estado) {
        Optional<Productos> productosOptional = productosRepository.findByEstado(estado);
        Productos productos;
        if (productosOptional.isPresent()) {
            productos = productosOptional.get();
            return modelMapper.map(productos, ProductosDTO.class);
        } else {
            throw new NotDataFound(NOEXISTENDATOS);
        }
    }

    @Override
    public ProductosDTO create(CrearProductosRequest crearProductosRequest) {
        Optional<Productos> productosOptional = productosRepository.findByNombreProducto(crearProductosRequest.getNombreProducto());
        if (productosOptional.isPresent()) {
            throw new AlreadyExists("producto ya existe");
        } else {
            Productos productos = new Productos();
            productos.setNombreProducto(crearProductosRequest.getNombreProducto());
            productos.setEstado(crearProductosRequest.getEstado());
            productos.setValorUnidad(crearProductosRequest.getValorUnidad());

            return modelMapper.map(productosRepository.save(productos), ProductosDTO.class);
        }
    }

    @Override
    public ProductosDTO update(ProductosRequest productosRequest) {
        Optional<Productos> productosOptional = productosRepository.findById(productosRequest.getId());
        if (productosOptional.isPresent()) {
            Productos productos = productosOptional.get();
            productos.setNombreProducto(productosRequest.getNombreProducto());
            productos.setEstado(productosRequest.getEstado());
            productos.setValorUnidad(productosRequest.getValorUnidad());
            productos = productosRepository.save(productos);
            return modelMapper.map(productos, ProductosDTO.class);
        } else {
            throw new NotDataFound(NOEXISTENDATOS);
        }
    }

    @Override
    public String delete(Long id) {
        Optional<Productos> productosOptional = Optional.ofNullable(productosRepository.findById(id)
                .orElseThrow(() -> new NotDataFound(" producto No existe: " + id)));
        if (productosOptional.isPresent()) {
            productosRepository.deleteById(id);

            return modelMapper.map(productosOptional.get(), ProductosDTO.class).getId() + "Eliminado con Exito";
        } else {
            throw new NotDataFound(NOEXISTENDATOS);
        }
    }
}
