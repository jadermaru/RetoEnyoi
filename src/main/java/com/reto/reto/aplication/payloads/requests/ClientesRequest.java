package com.reto.reto.aplication.payloads.requests;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;



@Setter
@Getter
public class ClientesRequest {
    @NonNull
    private Long id;
    @NonNull
    @NotBlank
    private String primerNombre;
    private String segundoNombre;
    @NonNull
    @NotBlank
    private String primerApellido ;
    private String segundoApellido ;
    @NonNull
    @NotBlank
    private String tipoDocumento  ;
    @NonNull
    private int numeroDocumento   ;
    private String dirección   ;
}
