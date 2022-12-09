package com.reto.reto.aplication.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;


@Data
@Entity
@NoArgsConstructor
public class Facturas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int numeroFactura;
    @Size(max = 120)
    private String nombredelcajero ;
    @Size(max = 120)
    private String numerodecaja;
    @NonNull
    @Size(max = 120)
    private String tipoDocumento ;
    @NonNull
    private int numeroDocumento ;
    @Size(max = 120)
    @NonNull
    private String nombreCliente  ;
    @JoinColumn(name = "cliente_id",referencedColumnName ="id",nullable = false)
    @ManyToOne(optional = false)
    private Clientes clienteId;

}
