package com.proyectofinal.notificationsmicroservice.repositories;

import com.proyectofinal.notificationsmicroservice.entities.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FacturaRepositories extends JpaRepository<Factura, Long> {
    List<Factura> findAllByUsername(String username);

    Factura findById(long id);
}
