package com.reto.reto.aplication.Services;


import com.reto.reto.aplication.dtos.FacturasDTO;
import com.reto.reto.aplication.payloads.requests.CrearFacturasRequest;
import com.reto.reto.aplication.payloads.requests.FacturasRequest;

import java.util.List;

public interface IFacturasService {
    List<FacturasDTO> findAll();
    FacturasDTO create(CrearFacturasRequest crearFacturasRequest);

    FacturasDTO update(FacturasRequest facturasRequest);
}
