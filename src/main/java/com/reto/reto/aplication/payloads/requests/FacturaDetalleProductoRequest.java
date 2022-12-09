package com.reto.reto.aplication.payloads.requests;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FacturaDetalleProductoRequest {

    private Long id;
    private int valorUnidad ;
    private int cantidad ;
    private int valorTotal ;
    private Long facturaid;
    private Long productoid;
}
