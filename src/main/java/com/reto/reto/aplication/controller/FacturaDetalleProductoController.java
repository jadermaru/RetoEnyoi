package com.reto.reto.aplication.controller;

import com.reto.reto.aplication.Services.IFacturasDetalleProductosService;
import com.reto.reto.aplication.dtos.ClientesDTO;
import com.reto.reto.aplication.dtos.FacturasDTO;
import com.reto.reto.aplication.dtos.FacturasDetalleProductoDTO;
import com.reto.reto.aplication.dtos.ProductosDTO;
import com.reto.reto.aplication.payloads.requests.CrearFacturaDetalleProductoRequest;
import com.reto.reto.aplication.payloads.requests.CrearFacturasRequest;
import com.reto.reto.aplication.payloads.requests.FacturaDetalleProductoRequest;
import com.reto.reto.base.utils.ResponseDTOService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/facturaDetalle")
@RequiredArgsConstructor
public class FacturaDetalleProductoController {
    private final ResponseDTOService responseDTOService;
    private final IFacturasDetalleProductosService facturasDetalleProductosService;

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
    @GetMapping("/ListarFacturasDetalle")
    public ResponseEntity<List<FacturasDetalleProductoDTO>> obtenerTodos() {
        return (ResponseEntity<List<FacturasDetalleProductoDTO>>) responseDTOService.response(facturasDetalleProductosService.findAll(), HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitud exitosa",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ClientesDTO.class))}),
            @ApiResponse(responseCode = "401", description = "debe iniciar session",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "sin privilegios suficientes",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "error al solicitar informacion",
                    content = @Content)})
    @GetMapping("/obtenerUno")
    public ResponseEntity<FacturasDetalleProductoDTO> obtenerUno(@RequestParam("id") Long id) {
        return (ResponseEntity<FacturasDetalleProductoDTO>) responseDTOService
                .response(facturasDetalleProductosService.findById(id), HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitud exitosa",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ClientesDTO.class))}),
            @ApiResponse(responseCode = "401", description = "debe iniciar session",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "sin privilegios suficientes",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "error al solicitar informacion",
                    content = @Content)})
    @GetMapping("/obtenerPorFactura")
    public ResponseEntity<Page<FacturasDetalleProductoDTO>> obtenerPorVenta(@RequestParam(value = "facturaid")Long facturaid,
                                                                    @RequestParam(name = "page", defaultValue = "0") int page,
                                                                    @RequestParam(name = "size", defaultValue = "10") int size,
                                                                    @RequestParam(name = "columnFilter", defaultValue = "id") String columnFilter,
                                                                    @RequestParam(name = "direction", defaultValue = "ASC") Sort.Direction direction){
        return (ResponseEntity<Page<FacturasDetalleProductoDTO>>) responseDTOService.response(facturasDetalleProductosService.findByFacturaid(facturaid,page,size,columnFilter,direction),HttpStatus.OK );
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
    @PostMapping("/RegistrarFacturaDetalle")
    public ResponseEntity<FacturasDetalleProductoDTO> create(@Valid @RequestBody List<CrearFacturaDetalleProductoRequest> crearFacturaDetalleProductoRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return (ResponseEntity<FacturasDetalleProductoDTO>) responseDTOService.response(HttpStatus.BAD_REQUEST);
        } else {
            return (ResponseEntity<FacturasDetalleProductoDTO>) responseDTOService.response(facturasDetalleProductosService.create(crearFacturaDetalleProductoRequest), HttpStatus.CREATED);
        }
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitud exitosa",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ClientesDTO.class))}),
            @ApiResponse(responseCode = "401", description = "debe iniciar session",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "sin privilegios suficientes",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "error al solicitar informacion",
                    content = @Content)})
    @PutMapping("/actualizarFacturaDetalle")
    public ResponseEntity<FacturasDetalleProductoDTO> actualizarVentasProductos(@Valid @RequestBody FacturaDetalleProductoRequest facturaDetalleProductoRequest,
                                                                        BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return (ResponseEntity<FacturasDetalleProductoDTO>) responseDTOService.response(HttpStatus.BAD_REQUEST);
        } else {
            return (ResponseEntity<FacturasDetalleProductoDTO>) responseDTOService.response(facturasDetalleProductosService.update(facturaDetalleProductoRequest),
                    HttpStatus.OK);
        }
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tipo_solicitud eliminada exitosamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ClientesDTO.class))}),
            @ApiResponse(responseCode = "401", description = "Debe iniciar sesion para eliminar",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Sin privilegios suficientes",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error al eliminar",
                    content = @Content)})
    @DeleteMapping("/eliminarUno")
    public ResponseEntity<String> eliminarUno(@RequestParam("id") long id) {
        return (ResponseEntity<String>) responseDTOService.response(facturasDetalleProductosService.delete(id), HttpStatus.OK);
    }

}
