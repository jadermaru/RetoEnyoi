package com.reto.reto.aplication.controller;

import com.reto.reto.aplication.Services.IFacturasService;
import com.reto.reto.aplication.dtos.ClientesDTO;
import com.reto.reto.aplication.dtos.FacturasDTO;
import com.reto.reto.aplication.dtos.ProductosDTO;
import com.reto.reto.aplication.payloads.requests.CrearFacturasRequest;
import com.reto.reto.aplication.payloads.requests.FacturasRequest;
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
@RequestMapping("/facturas")
@RequiredArgsConstructor
public class FacturasController {
    private final ResponseDTOService responseDTOService;
    private final IFacturasService facturasService;
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
    @GetMapping("/ListarFacturas")
    public ResponseEntity<List<FacturasDTO>> obtenerTodos() {
        return (ResponseEntity<List<FacturasDTO>>) responseDTOService.response(facturasService.findAll(), HttpStatus.OK);
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
    @PostMapping("/RegistrarFactura")
    public ResponseEntity<FacturasDTO> create(@Valid @RequestBody CrearFacturasRequest crearFacturasRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return (ResponseEntity<FacturasDTO>) responseDTOService.response(HttpStatus.BAD_REQUEST);
        } else {
            return (ResponseEntity<FacturasDTO>) responseDTOService.response(facturasService.create(crearFacturasRequest), HttpStatus.CREATED);
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
    @PutMapping("/ActualizarFactura")
    public ResponseEntity<FacturasDTO> actualizarFactura(@Valid @RequestBody FacturasRequest facturasRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return (ResponseEntity<FacturasDTO>) responseDTOService.response(HttpStatus.BAD_REQUEST);
        } else {
            return (ResponseEntity<FacturasDTO>) responseDTOService.response(facturasService.update(facturasRequest), HttpStatus.OK);
        }
    }

}
