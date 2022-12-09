package com.reto.reto.aplication.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductosDTO {
    private Long id;
    private String nombreProducto ;
    private int valorUnidad   ;
    private String estado ;
}
