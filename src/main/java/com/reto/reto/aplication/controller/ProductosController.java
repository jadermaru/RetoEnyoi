package com.reto.reto.aplication.controller;

import com.reto.reto.aplication.Services.IProductosService;
import com.reto.reto.aplication.dtos.ProductosDTO;
import com.reto.reto.aplication.payloads.requests.CrearProductosRequest;
import com.reto.reto.aplication.payloads.requests.ProductosRequest;
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
@RequestMapping("/productos")
@RequiredArgsConstructor
public class ProductosController {
    private final ResponseDTOService responseDTOService;
    private final IProductosService productosService;

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
    @GetMapping("/obtenerTodos")
    public ResponseEntity<List<ProductosDTO>> obtenerTodos() {
        return (ResponseEntity<List<ProductosDTO>>) responseDTOService.response(productosService.findAll(), HttpStatus.OK);
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
    @GetMapping("/ObtenerProducto")
    public ResponseEntity<ProductosDTO> obtenerUnoId(@RequestParam("id") long id) {
        return (ResponseEntity<ProductosDTO>) responseDTOService.response(productosService.findById(id), HttpStatus.OK);
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
    public ResponseEntity<ProductosDTO> obtenerporNombre(@RequestParam("nombre") String nombre) {
        return (ResponseEntity<ProductosDTO>) responseDTOService.response(productosService.findByNombreProducto(nombre), HttpStatus.OK);
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Producto creado exitosamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductosDTO.class))}),
            @ApiResponse(responseCode = "401", description = "Debe iniciar sesion para crear la Producto",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Sin privilegios suficientes",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error al crear Producto",
                    content = @Content)})
    @PostMapping("/AgregarProducto")
    public ResponseEntity<ProductosDTO> create(@Valid @RequestBody CrearProductosRequest crearProductosRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return (ResponseEntity<ProductosDTO>) responseDTOService.response(HttpStatus.BAD_REQUEST);
        } else {
            return (ResponseEntity<ProductosDTO>) responseDTOService.response(productosService.create(crearProductosRequest), HttpStatus.CREATED);
        }
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Productos actualizada exitosamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductosDTO.class))}),
            @ApiResponse(responseCode = "401", description = "Debe iniciar sesion para actualizar",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Sin privilegios suficientes",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error al actualizar",
                    content = @Content)})
    @PutMapping("/ActualizaPrecioProducto")
    public ResponseEntity<ProductosDTO> updateProductos(@Valid @RequestBody ProductosRequest productosRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return (ResponseEntity<ProductosDTO>) responseDTOService.response(HttpStatus.BAD_REQUEST);
        } else {
            return (ResponseEntity<ProductosDTO>) responseDTOService.response(productosService.update(productosRequest), HttpStatus.OK);
        }
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Productos eliminado exitosamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductosDTO.class))}),
            @ApiResponse(responseCode = "401", description = "Debe iniciar sesion para eliminar",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Sin privilegios suficientes",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error al eliminar",
                    content = @Content)})
    @DeleteMapping("/EliminarProducto")
    public ResponseEntity<String> eliminarUno(@RequestParam("id") long id) {
        return (ResponseEntity<String>) responseDTOService.response(productosService.delete(id), HttpStatus.OK);
    }
}
