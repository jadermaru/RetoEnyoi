package com.reto.reto.aplication.Repositories;

import com.reto.reto.aplication.entities.Productos;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductosRepository extends JpaRepository<Productos,Long> {
    @Override
    Optional<Productos> findById(Long id);
    Optional<Productos> findByNombreProducto(String nombreProducto);
    Optional<Productos> findByEstado(String estado);
}
