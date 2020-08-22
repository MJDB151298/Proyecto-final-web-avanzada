package sym.eventmicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sym.eventmicroservice.entities.Parametro;

@Repository
public interface ParametroRepository extends JpaRepository<Parametro, Integer> {
}