package com.reto.reto.aplication.dtos;

import com.reto.reto.aplication.entities.Clientes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FacturasDTO {
    private Long id;
    private int numeroFactura;
    private String nombredelcajero ;
    private String numerodecaja;
    private String tipoDocumento ;
    private int numeroDocumento ;
    private String nombreCliente  ;
    private Clientes clienteId;
}
