package com.reto.reto.aplication.controller;

import com.reto.reto.aplication.Services.IClientesService;
import com.reto.reto.aplication.dtos.ClientesDTO;
import com.reto.reto.aplication.dtos.ProductosDTO;
import com.reto.reto.aplication.payloads.requests.ClientesRequest;
import com.reto.reto.aplication.payloads.requests.CrearClientesRequest;
import com.reto.reto.base.utils.ResponseDTOService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClientesController {
    private final ResponseDTOService responseDTOService;
    private final IClientesService clientesService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "data found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ClientesDTO.class))}),
            @ApiResponse(responseCode = "401", description = "debe iniciar session",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "sin privilegios suficientes",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "error al solicitar informacion",
                    content = @Content)})
    @GetMapping("/obtenerTodos")
    public ResponseEntity<List<ClientesDTO>> obtenerTodos() {
        return (ResponseEntity<List<ClientesDTO>>) responseDTOService.response(clientesService.findAll(), HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "data found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductosDTO.class))}),
            @ApiResponse(responseCode = "401", description = "debe iniciar session",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "sin privilegios suficientes",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "error al solicitar informacion",
                    content = @Content)})
    @GetMapping("/ObtenerCliente")
    public ResponseEntity<ClientesDTO> obtenerUnoId(@RequestParam("id") long id) {
        return (ResponseEntity<ClientesDTO>) responseDTOService.response(clientesService.findById(id), HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "data found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductosDTO.class))}),
            @ApiResponse(responseCode = "401", description = "debe iniciar session",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "sin privilegios suficientes",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "error al solicitar informacion",
                    content = @Content)})
    @GetMapping("/obtenerUnoporNombre")
    public ResponseEntity<ClientesDTO> obtenerporNombre(@RequestParam("primerNombre") String primerNombre) {
        return (ResponseEntity<ClientesDTO>) responseDTOService.response(clientesService.findByPrimerNombre(primerNombre), HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "data found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductosDTO.class))}),
            @ApiResponse(responseCode = "401", description = "debe iniciar session",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "sin privilegios suficientes",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "error al solicitar informacion",
                    content = @Content)})
    @GetMapping("/obtenerUnoporCedula")
    public ResponseEntity<ClientesDTO> obtenerporNombre(@RequestParam("numeroDocumento") int numeroDocumento) {
        return (ResponseEntity<ClientesDTO>) responseDTOService.response(clientesService.findByNumeroDocumento(numeroDocumento), HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cliente creado exitosamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductosDTO.class))}),
            @ApiResponse(responseCode = "401", description = "Debe iniciar sesion para crear la Cliente",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Sin privilegios suficientes",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error al crear Cliente",
                    content = @Content)})
    @PostMapping("/AgregarCliente")
    public ResponseEntity<ClientesDTO> create(@Valid @RequestBody CrearClientesRequest crearClientesRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return (ResponseEntity<ClientesDTO>) responseDTOService.response(HttpStatus.BAD_REQUEST);
        } else {
            return (ResponseEntity<ClientesDTO>) responseDTOService.response(clientesService.create(crearClientesRequest), HttpStatus.CREATED);
        }
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cliente actualizado exitosamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductosDTO.class))}),
            @ApiResponse(responseCode = "401", description = "Debe iniciar sesion para actualizar",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Sin privilegios suficientes",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error al actualizar",
                    content = @Content)})
    @PutMapping("/ActualizarCliente")
    public ResponseEntity<ClientesDTO> actualizarClientes(@Valid @RequestBody ClientesRequest clientesRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return (ResponseEntity<ClientesDTO>) responseDTOService.response(HttpStatus.BAD_REQUEST);
        } else {
            return (ResponseEntity<ClientesDTO>) responseDTOService.response(clientesService.update(clientesRequest), HttpStatus.OK);
        }
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cliente eliminado exitosamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductosDTO.class))}),
            @ApiResponse(responseCode = "401", description = "Debe iniciar sesion para eliminar",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Sin privilegios suficientes",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error al eliminar",
                    content = @Content)})
    @DeleteMapping("/EliminarCliente")
    public ResponseEntity<String> eliminarUno(@RequestParam("id") long id) {
        return (ResponseEntity<String>) responseDTOService.response(clientesService.delete(id), HttpStatus.OK);
    }

}
