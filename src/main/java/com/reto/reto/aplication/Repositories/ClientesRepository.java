package com.reto.reto.aplication.Repositories;

import com.reto.reto.aplication.entities.Clientes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientesRepository extends JpaRepository<Clientes,Long> {
    Optional<Clientes> findById(Long id);
    Optional<Clientes> findByNumeroDocumento(int numeroDocumento);
    Optional<Clientes> findByPrimerNombre(String primerNombre);

}
