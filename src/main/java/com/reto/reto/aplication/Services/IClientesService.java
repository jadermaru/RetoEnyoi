package com.reto.reto.aplication.Services;

import com.reto.reto.aplication.dtos.ClientesDTO;
import com.reto.reto.aplication.payloads.requests.ClientesRequest;
import com.reto.reto.aplication.payloads.requests.CrearClientesRequest;

import java.util.List;

public interface IClientesService {
    List<ClientesDTO> findAll();

    ClientesDTO findById(Long id);

    ClientesDTO findByNumeroDocumento(int numeroDocumento);

    ClientesDTO findByPrimerNombre(String primerNombre);

    ClientesDTO create(CrearClientesRequest crearClientesRequest);

    ClientesDTO update(ClientesRequest clientesRequest);

    String delete(Long id);
}
