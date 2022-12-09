package com.reto.reto.aplication.dtos;

import com.reto.reto.aplication.entities.Facturas;
import com.reto.reto.aplication.entities.Productos;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FacturasDetalleProductoDTO {
    private Long id;
    private int valorUnidad ;
    private int cantidad ;
    private int valorTotal ;
    private Facturas facturaid;
    private Productos productoid;
}
