package com.reto.reto.aplication.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;



@Getter
@Setter
@NoArgsConstructor
@Entity
public class Productos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    @Size(max = 250)
    private String nombreProducto ;
    @NonNull
    private int valorUnidad   ;
    @NonNull
    @Size(max = 120)
    private String estado ;
}
