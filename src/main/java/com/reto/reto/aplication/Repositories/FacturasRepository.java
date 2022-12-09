package com.reto.reto.aplication.Repositories;

import com.reto.reto.aplication.entities.Facturas;
import com.reto.reto.aplication.entities.Productos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FacturasRepository extends JpaRepository<Facturas,Long> {
    @Override
    Optional<Facturas> findById(Long id);
    Optional<Facturas> findByNumeroFactura(int numeroFactura);
}
