package com.reto.reto.aplication.Services.impl;

import com.reto.reto.aplication.Repositories.FacturasDetalleProductoRepository;
import com.reto.reto.aplication.Repositories.FacturasRepository;
import com.reto.reto.aplication.Repositories.ProductosRepository;
import com.reto.reto.aplication.Services.IFacturasDetalleProductosService;
import com.reto.reto.aplication.dtos.FacturasDetalleProductoDTO;
import com.reto.reto.aplication.entities.Facturas;
import com.reto.reto.aplication.entities.FacturasDetalleProducto;
import com.reto.reto.aplication.entities.Productos;
import com.reto.reto.aplication.payloads.requests.CrearFacturaDetalleProductoRequest;
import com.reto.reto.aplication.payloads.requests.FacturaDetalleProductoRequest;
import com.reto.reto.base.exceptions.AlreadyExists;
import com.reto.reto.base.exceptions.NotDataFound;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FacturasDetalleProductoServices implements IFacturasDetalleProductosService {
    //Utilidades
    private final ModelMapper modelMapper;
    private static final String NOEXISTENDATOS = "No existen datos";
    //Repositorios
    private final FacturasRepository facturasRepository;
    private final FacturasDetalleProductoRepository facturasDetalleProductoRepository;
    private final ProductosRepository productosRepository;

    @Override
    public List<FacturasDetalleProductoDTO> findAll() {
        List<FacturasDetalleProducto> facturasDetalleProductoList = facturasDetalleProductoRepository.findAll();
        List<FacturasDetalleProductoDTO> facturasDetalleProductoDTOList = new ArrayList<>();

        facturasDetalleProductoList.forEach(facturasDetalleProducto -> {
            FacturasDetalleProductoDTO facturasDetalleProductoDTO = modelMapper.map(facturasDetalleProducto, FacturasDetalleProductoDTO.class);
            facturasDetalleProductoDTOList.add(facturasDetalleProductoDTO);
        });

        return facturasDetalleProductoDTOList;
    }

    @Override
    public FacturasDetalleProductoDTO findById(Long id) {
        Optional<FacturasDetalleProducto> facturasDetalleProductoOptional = facturasDetalleProductoRepository.findById(id);
        FacturasDetalleProducto facturasDetalleProducto;
        if (facturasDetalleProductoOptional.isPresent()) {
            facturasDetalleProducto = facturasDetalleProductoOptional.get();
            return modelMapper.map(facturasDetalleProducto, FacturasDetalleProductoDTO.class);
        } else {
            throw new NotDataFound(NOEXISTENDATOS);
        }
    }

    @Override
    public Page<FacturasDetalleProductoDTO> findByFacturaid(Long facturaid, int page, int size, String columnFilter, Sort.Direction direction) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, columnFilter));
        Facturas facturas = facturasRepository.findById(facturaid)
                .orElseThrow(() -> new NotDataFound(NOEXISTENDATOS));
        List<FacturasDetalleProductoDTO> list = facturasDetalleProductoRepository.findByFacturaid(facturas, pageable)
                .stream()
                .map(facturasDetalleProducto -> modelMapper.map(facturasDetalleProducto, FacturasDetalleProductoDTO.class))
                .collect(Collectors.toList());
        final int start = (int) pageable.getOffset();
        final int end = Math.min((start + pageable.getPageSize()), list.size());
        return new PageImpl<>(list.subList(start, end), pageable, list.size());
    }

    @Override
    public List<FacturasDetalleProductoDTO> create(List<CrearFacturaDetalleProductoRequest> crearFacturaDetalleProductoRequest) {
        List<FacturasDetalleProducto> ventasProductosList = new ArrayList<>();
        List<FacturasDetalleProductoDTO> facturasDetalleProductoDTOList = new ArrayList<>();
        crearFacturaDetalleProductoRequest.forEach(facturasDetalleProducto -> {
            var facturas = facturasRepository.findById(facturasDetalleProducto.getFacturaid());

            if (facturas.isPresent()) {
                FacturasDetalleProducto facturasDetalleProductoCrear = new FacturasDetalleProducto();
                facturasDetalleProductoCrear.setFacturaid(facturas.get());
                Productos productos = productosRepository.findById(facturasDetalleProducto.getProductoid())
                        .orElseThrow(() -> new NotDataFound(NOEXISTENDATOS));
                facturasDetalleProductoCrear.setProductoid(productos);

                facturasDetalleProductoCrear.setValorUnidad(facturasDetalleProducto.getValorUnidad());
                facturasDetalleProductoCrear.setCantidad(facturasDetalleProducto.getCantidad());
                facturasDetalleProductoCrear.setValorTotal(facturasDetalleProducto.getValorTotal());

                FacturasDetalleProductoDTO facturasDetalleProductoDTO = modelMapper.map(facturasDetalleProductoRepository.save(facturasDetalleProductoCrear),
                        FacturasDetalleProductoDTO.class);
                facturasDetalleProductoDTOList.add(facturasDetalleProductoDTO);

            } else {

                throw new AlreadyExists("Número de venta no existe");
            }
        });
        return facturasDetalleProductoDTOList;
    }

    @Override
    public FacturasDetalleProductoDTO update(FacturaDetalleProductoRequest facturaDetalleProductoRequest) {
        Optional<FacturasDetalleProducto> facturasDetalleProductoOptional = facturasDetalleProductoRepository.findById(facturaDetalleProductoRequest.getId());
        if (facturasDetalleProductoOptional.isPresent()){
            FacturasDetalleProducto facturasDetalleProductoUpdate = facturasDetalleProductoOptional.get();
            Productos productos = productosRepository.findById(facturaDetalleProductoRequest.getProductoid())
                    .orElseThrow(() -> new NotDataFound(NOEXISTENDATOS));
            facturasDetalleProductoUpdate.setProductoid(productos);
            Facturas facturas = facturasRepository.findById(facturaDetalleProductoRequest.getFacturaid())
                    .orElseThrow(() -> new NotDataFound(NOEXISTENDATOS));
            facturasDetalleProductoUpdate.setFacturaid(facturas);

            facturasDetalleProductoUpdate.setValorUnidad(facturaDetalleProductoRequest.getValorUnidad());
            facturasDetalleProductoUpdate.setCantidad(facturaDetalleProductoRequest.getCantidad());
            facturasDetalleProductoUpdate.setValorTotal(facturaDetalleProductoRequest.getValorTotal());

            facturasDetalleProductoUpdate = facturasDetalleProductoRepository.save(facturasDetalleProductoUpdate);
            return modelMapper.map(facturasDetalleProductoUpdate, FacturasDetalleProductoDTO.class);

        }else {

            throw new NotDataFound(NOEXISTENDATOS);
        }
    }

    @Override
    public String delete(Long id) {
        Optional<FacturasDetalleProducto> facturasDetalleProductoOptional = Optional.ofNullable(facturasDetalleProductoRepository.findById(id)
                .orElseThrow(() -> new NotDataFound(" Detalle producto No existe: " + id)));
        if (facturasDetalleProductoOptional.isPresent()) {
            facturasDetalleProductoRepository.deleteById(id);

            return modelMapper.map(facturasDetalleProductoOptional.get(), FacturasDetalleProductoDTO.class).getId() + "Eliminado con Exito";
        } else {
            throw new NotDataFound(NOEXISTENDATOS);
        }
    }
}
