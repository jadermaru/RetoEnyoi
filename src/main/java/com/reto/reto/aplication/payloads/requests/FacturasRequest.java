package com.reto.reto.aplication.payloads.requests;

import com.reto.reto.aplication.entities.Clientes;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Setter
@Getter
public class FacturasRequest {
    @NonNull
    private Long id;
    @NonNull
    private int numeroFactura;
    private String nombredelcajero ;
    private String numerodecaja;
    private String tipoDocumento ;
    private int numeroDocumento ;
    private String nombreCliente  ;
    @NonNull
    private Long clienteId;
}
