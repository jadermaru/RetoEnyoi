package com.reto.reto.aplication.Services.impl;

import com.reto.reto.aplication.Repositories.ClientesRepository;
import com.reto.reto.aplication.Repositories.FacturasRepository;
import com.reto.reto.aplication.Services.IFacturasService;
import com.reto.reto.aplication.dtos.FacturasDTO;
import com.reto.reto.aplication.entities.Clientes;
import com.reto.reto.aplication.entities.Facturas;
import com.reto.reto.aplication.payloads.requests.CrearFacturasRequest;
import com.reto.reto.aplication.payloads.requests.FacturasRequest;
import com.reto.reto.base.exceptions.AlreadyExists;
import com.reto.reto.base.exceptions.NotDataFound;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FacturasService implements IFacturasService {
    //Utilidades
    private final ModelMapper modelMapper;
    private static final String NOEXISTENDATOS = "No existen datos";
    //Repositorios
    private final FacturasRepository facturasRepository;
    private final ClientesRepository clientesRepository;

    @Override
    public List<FacturasDTO> findAll() {
        List<Facturas> facturasList = facturasRepository.findAll();
        List<FacturasDTO> facturasDTOList = new ArrayList<>();

        facturasList.forEach(facturas -> {
            FacturasDTO facturasDTO = modelMapper.map(facturas, FacturasDTO.class);
            facturasDTOList.add(facturasDTO);
        });

        return facturasDTOList;
    }

    @Override
    public FacturasDTO create(CrearFacturasRequest crearFacturasRequest) {

        Optional<Facturas> facturasOptional = facturasRepository.findByNumeroFactura(crearFacturasRequest.getNumeroFactura());
        if (facturasOptional.isPresent()) {
            throw new AlreadyExists("Factura ya existe");
        } else {
            Facturas facturas = new Facturas();
            facturas.setNumeroFactura(crearFacturasRequest.getNumeroFactura());
            facturas.setNombredelcajero(crearFacturasRequest.getNombredelcajero());
            facturas.setNumerodecaja(crearFacturasRequest.getNumerodecaja());

            Clientes clientes = clientesRepository.findById(crearFacturasRequest.getClienteId())
                    .orElseThrow(() -> new NotDataFound(NOEXISTENDATOS));
            facturas.setClienteId(clientes);
            facturas.setTipoDocumento(clientes.getTipoDocumento());
            facturas.setNumeroDocumento(clientes.getNumeroDocumento());
            facturas.setNombreCliente(clientes.getPrimerNombre());

            return modelMapper.map(facturasRepository.save(facturas), FacturasDTO.class);
        }
    }

    @Override
    public FacturasDTO update(FacturasRequest facturasRequest) {
        Optional<Facturas> facturasOptional = facturasRepository.findById(facturasRequest.getId());
        if (facturasOptional.isPresent()) {
            Facturas facturas = facturasOptional.get();
            facturas.setNumeroFactura(facturasRequest.getNumeroFactura());
            facturas.setNombredelcajero(facturasRequest.getNombredelcajero());
            facturas.setNumerodecaja(facturasRequest.getNumerodecaja());

            Clientes clientes = clientesRepository.findById(facturasRequest.getClienteId())
                    .orElseThrow(() -> new NotDataFound(NOEXISTENDATOS));
            facturas.setClienteId(clientes);
            facturas.setTipoDocumento(clientes.getTipoDocumento());
            facturas.setNumeroDocumento(clientes.getNumeroDocumento());
            facturas.setNombreCliente(clientes.getPrimerNombre());

            facturas = facturasRepository.save(facturas);
            return modelMapper.map(facturas, FacturasDTO.class);

        } else {
            throw new NotDataFound(NOEXISTENDATOS);
        }
    }

}
