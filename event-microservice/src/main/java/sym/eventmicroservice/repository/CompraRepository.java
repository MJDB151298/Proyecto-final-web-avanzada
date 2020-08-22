package sym.eventmicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sym.eventmicroservice.entities.Compra;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Long> {
}