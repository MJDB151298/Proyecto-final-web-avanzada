package edu.pucmm.eict.springbootpaypal.repositorios;

import edu.pucmm.eict.springbootpaypal.entidades.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Long> {
}
