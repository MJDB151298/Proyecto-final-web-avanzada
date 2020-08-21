package sym.eventmicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sym.eventmicroservice.entities.Evento;

public interface EventRepository extends JpaRepository<Evento, String> {
    Evento findByNombre (String nombre);
}
