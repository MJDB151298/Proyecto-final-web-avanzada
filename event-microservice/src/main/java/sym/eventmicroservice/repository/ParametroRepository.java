package edu.pucmm.eict.springbootpaypal.repositorios;

import edu.pucmm.eict.springbootpaypal.entidades.Parametro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParametroRepository extends JpaRepository<Parametro, Integer> {
}
