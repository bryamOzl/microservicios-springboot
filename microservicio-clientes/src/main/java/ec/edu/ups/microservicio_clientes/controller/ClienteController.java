package ec.edu.ups.microservicio_clientes.controller;

import ec.edu.ups.microservicio_clientes.model.Cliente;
import ec.edu.ups.microservicio_clientes.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    public List<Cliente> obtenerClientes() {
        return clienteRepository.findAll();
    }

    @PostMapping
    public Cliente crearCliente(@RequestBody Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> obtenerClientePorId(@PathVariable Long id) {
        return clienteRepository.findById(id)
                .map(cliente -> ResponseEntity.ok(cliente))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> actualizarCliente(@PathVariable Long id, @RequestBody Cliente clienteDetalles) {
        return clienteRepository.findById(id)
                .map(cliente -> {
                    cliente.setNombre(clienteDetalles.getNombre());
                    cliente.setEmail(clienteDetalles.getEmail());
                    Cliente actualizado = clienteRepository.save(cliente);
                    return ResponseEntity.ok(actualizado);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable Long id) {
        return clienteRepository.findById(id)
                .map(cliente -> {
                    clienteRepository.delete(cliente);
                    return ResponseEntity.noContent().<Void>build(); // 204 No Content
                })
                .orElse(ResponseEntity.notFound().<Void>build()); // 404 Not Found
    }
}
