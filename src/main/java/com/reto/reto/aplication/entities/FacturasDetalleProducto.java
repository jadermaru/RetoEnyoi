package com.reto.reto.aplication.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Entity
@NoArgsConstructor
public class FacturasDetalleProducto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private int valorUnidad ;
    @NonNull
    private int cantidad ;
    @NonNull
    private int valorTotal ;
    @JoinColumn(name = "Factura_id",referencedColumnName ="id",nullable = false)
    @ManyToOne(optional = false)
    private Facturas facturaid;
    @JoinColumn(name = "Producto_id",referencedColumnName ="id",nullable = false)
    @ManyToOne(optional = false)
    private Productos productoid;
}
