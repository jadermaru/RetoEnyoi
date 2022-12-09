package com.reto.reto.aplication.payloads.requests;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;


@Setter
@Getter
public class ProductosRequest {
    @NonNull
    private Long id;
    @NonNull
    @NotBlank
    private String nombreProducto;
    @NonNull
    private int valorUnidad;
    @NonNull
    @NotBlank
    private String estado;
}
