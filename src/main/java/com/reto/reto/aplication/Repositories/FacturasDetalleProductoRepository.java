package com.reto.reto.aplication.Repositories;


import com.reto.reto.aplication.entities.Facturas;
import com.reto.reto.aplication.entities.FacturasDetalleProducto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FacturasDetalleProductoRepository extends JpaRepository<FacturasDetalleProducto,Long> {
    @Override
    Optional<FacturasDetalleProducto> findById(Long id);
    Page<FacturasDetalleProducto> findByFacturaid(Facturas facturasId, Pageable pageable);
}
