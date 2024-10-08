package ec.edu.ups.microservicio_clientes.repository;

import ec.edu.ups.microservicio_clientes.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
