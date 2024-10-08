package ec.edu.ups.microservicio_ordenes.controller;

import ec.edu.ups.microservicio_ordenes.model.Orden;
import ec.edu.ups.microservicio_ordenes.repository.OrdenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ordenes")
public class OrdenController {

    @Autowired
    private OrdenRepository ordenRepository;

    @GetMapping
    public List<Orden> obtenerOrdenes() {
        return ordenRepository.findAll();
    }

    @PostMapping
    public Orden crearOrden(@RequestBody Orden orden) {
        return ordenRepository.save(orden);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Orden> obtenerOrdenPorId(@PathVariable Long id) {
        return ordenRepository.findById(id)
                .map(orden -> ResponseEntity.ok(orden))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Orden> actualizarOrden(@PathVariable Long id, @RequestBody Orden ordenDetalles) {
        return ordenRepository.findById(id)
                .map(orden -> {
                    orden.setClienteId(ordenDetalles.getClienteId());
                    orden.setProducto(ordenDetalles.getProducto());
                    Orden actualizado = ordenRepository.save(orden);
                    return ResponseEntity.ok(actualizado);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarOrden(@PathVariable Long id) {
        return ordenRepository.findById(id)
                .map(orden -> {
                    ordenRepository.delete(orden);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
