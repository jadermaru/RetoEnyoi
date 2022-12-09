package com.reto.reto.aplication.Services;


import com.reto.reto.aplication.dtos.FacturasDetalleProductoDTO;
import com.reto.reto.aplication.payloads.requests.CrearFacturaDetalleProductoRequest;
import com.reto.reto.aplication.payloads.requests.FacturaDetalleProductoRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface IFacturasDetalleProductosService {
    List<FacturasDetalleProductoDTO> findAll();

    FacturasDetalleProductoDTO findById(Long id);

    Page<FacturasDetalleProductoDTO> findByFacturaid(Long facturaid, int page, int size, String columnFilter, Sort.Direction direction);

    List<FacturasDetalleProductoDTO> create(List<CrearFacturaDetalleProductoRequest>  crearFacturaDetalleProductoRequest);

    FacturasDetalleProductoDTO update(FacturaDetalleProductoRequest facturaDetalleProductoRequest);

    String delete(Long id);
}
