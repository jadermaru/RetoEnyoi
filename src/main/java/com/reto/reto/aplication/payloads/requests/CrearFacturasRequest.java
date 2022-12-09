package com.reto.reto.aplication.payloads.requests;


import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Setter
@Getter
public class CrearFacturasRequest {


    private int numeroFactura;
    private String nombredelcajero ;
    private String numerodecaja;
    @NonNull
    private Long clienteId;
}
