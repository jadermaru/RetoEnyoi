package com.reto.reto.aplication.Services.impl;

import com.reto.reto.aplication.Repositories.ClientesRepository;
import com.reto.reto.aplication.Repositories.ProductosRepository;
import com.reto.reto.aplication.Services.IClientesService;
import com.reto.reto.aplication.dtos.ClientesDTO;
import com.reto.reto.aplication.entities.Clientes;
import com.reto.reto.aplication.payloads.requests.ClientesRequest;
import com.reto.reto.aplication.payloads.requests.CrearClientesRequest;
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
public class ClientesService implements IClientesService {
    //Utilidades
    private final ModelMapper modelMapper;
    private static final String NOEXISTENDATOS = "No existen datos";
    //Repositorios
    private final ClientesRepository clientesRepository;

    @Override
    public List<ClientesDTO> findAll() {
        List<Clientes> clientesList = clientesRepository.findAll();
        List<ClientesDTO> clientesDTOList = new ArrayList<>();

        clientesList.forEach(clientes -> {
            ClientesDTO clientesDTO = modelMapper.map(clientes, ClientesDTO.class);
            clientesDTOList.add(clientesDTO);
        });

        return clientesDTOList;
    }

    @Override
    public ClientesDTO findById(Long id) {
        Optional<Clientes> clientesOptional = clientesRepository.findById(id);
        Clientes clientes;
        if (clientesOptional.isPresent()) {
            clientes = clientesOptional.get();
            return modelMapper.map(clientes, ClientesDTO.class);
        } else {
            throw new NotDataFound(NOEXISTENDATOS);
        }
    }

    @Override
    public ClientesDTO findByNumeroDocumento(int numeroDocumento) {
        Optional<Clientes> clientesOptional = clientesRepository.findByNumeroDocumento(numeroDocumento);
        Clientes clientes;
        if (clientesOptional.isPresent()) {
            clientes = clientesOptional.get();
            return modelMapper.map(clientes, ClientesDTO.class);
        } else {
            throw new NotDataFound(NOEXISTENDATOS);
        }
    }

    @Override
    public ClientesDTO findByPrimerNombre(String primerNombre) {
        Optional<Clientes> clientesOptional = clientesRepository.findByPrimerNombre(primerNombre);
        Clientes clientes;
        if (clientesOptional.isPresent()) {
            clientes = clientesOptional.get();
            return modelMapper.map(clientes, ClientesDTO.class);
        } else {
            throw new NotDataFound(NOEXISTENDATOS);
        }
    }

    @Override
    public ClientesDTO create(CrearClientesRequest crearClientesRequest) {
        Optional<Clientes> clientesOptional = clientesRepository.findByNumeroDocumento(crearClientesRequest.getNumeroDocumento());
        if (clientesOptional.isPresent()) {
            throw new AlreadyExists("Cliente ya existe");
        } else {
            Clientes clientes = new Clientes();
            clientes.setPrimerNombre(crearClientesRequest.getPrimerNombre());
            clientes.setSegundoNombre(crearClientesRequest.getSegundoNombre());
            clientes.setPrimerApellido(crearClientesRequest.getPrimerApellido());
            clientes.setSegundoApellido(crearClientesRequest.getSegundoApellido());
            clientes.setTipoDocumento(crearClientesRequest.getTipoDocumento());
            clientes.setNumeroDocumento(crearClientesRequest.getNumeroDocumento());
            clientes.setDirección(crearClientesRequest.getDirección());

            return modelMapper.map(clientesRepository.save(clientes), ClientesDTO.class);
        }
    }

    @Override
    public ClientesDTO update(ClientesRequest clientesRequest) {
        Optional<Clientes> clientesOptional = clientesRepository.findById(clientesRequest.getId());
        if (clientesOptional.isPresent()) {
            Clientes clientes = clientesOptional.get();
            clientes.setPrimerNombre(clientesRequest.getPrimerNombre());
            clientes.setSegundoNombre(clientesRequest.getSegundoNombre());
            clientes.setPrimerApellido(clientesRequest.getPrimerApellido());
            clientes.setSegundoApellido(clientesRequest.getSegundoApellido());
            clientes.setTipoDocumento(clientesRequest.getTipoDocumento());
            clientes.setNumeroDocumento(clientesRequest.getNumeroDocumento());
            clientes.setDirección(clientesRequest.getDirección());
            clientes = clientesRepository.save(clientes);
            return modelMapper.map(clientes, ClientesDTO.class);

        } else {
            throw new NotDataFound(NOEXISTENDATOS);
        }
    }

    @Override
    public String delete(Long id) {
        Optional<Clientes> clientesOptional = Optional.ofNullable(clientesRepository.findById(id)
                .orElseThrow(() -> new NotDataFound(" Cliente No existe: " + id)));
        if (clientesOptional.isPresent()) {
            clientesRepository.deleteById(id);

            return modelMapper.map(clientesOptional.get(), ClientesDTO.class).getId() + "Eliminado con Exito";
        } else {
            throw new NotDataFound(NOEXISTENDATOS);
        }
    }
}
