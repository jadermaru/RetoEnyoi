package com.reto.reto.aplication.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;


@Data
@Entity
@NoArgsConstructor
public class Clientes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Size(max = 120)
    @NonNull
    private String primerNombre;
    @Size(max = 120)
    @NonNull
    private String segundoNombre;
    @Size(max = 120)
    private String primerApellido ;
    @Size(max = 120)
    private String segundoApellido ;
    @Size(max = 120)
    @NonNull
    private String tipoDocumento  ;
    @NonNull
    private int numeroDocumento   ;
    @Size(max = 120)
    private String dirección   ;

}
