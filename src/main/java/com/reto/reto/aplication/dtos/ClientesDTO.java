package com.reto.reto.aplication.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientesDTO {
    private Long id;
    private String primerNombre;
    private String segundoNombre;
    private String primerApellido ;
    private String segundoApellido ;
    private String tipoDocumento  ;
    private int numeroDocumento   ;
    private String dirección   ;
}
