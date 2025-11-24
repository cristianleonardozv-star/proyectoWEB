package com.universidad.elecciones.restcontroller;

import com.universidad.elecciones.dto.TipoDTO;
import com.universidad.elecciones.service.TipoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tipos")
@CrossOrigin(origins = "*")
public class TipoRestController {

    private final TipoService service;

    public TipoRestController(TipoService service) {
        this.service = service;
    }

    @GetMapping
    public List<TipoDTO> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public TipoDTO obtener(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    public ResponseEntity<TipoDTO> crear(@RequestBody TipoDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(dto));
    }

    @PutMapping("/{id}")
    public TipoDTO actualizar(@PathVariable Long id, @RequestBody TipoDTO dto) {
        return service.actualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
