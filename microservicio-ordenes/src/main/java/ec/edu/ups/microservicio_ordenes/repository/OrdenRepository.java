package ec.edu.ups.microservicio_ordenes.repository;

import ec.edu.ups.microservicio_ordenes.model.Orden;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdenRepository extends JpaRepository<Orden, Long> {
}
