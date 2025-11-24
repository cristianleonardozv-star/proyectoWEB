package com.universidad.elecciones.restcontroller;

import com.universidad.elecciones.dto.TipoEleccionDTO;
import com.universidad.elecciones.service.TipoEleccionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tipo-eleccion")
@CrossOrigin(origins = "*")
public class TipoEleccionRestController {

    private final TipoEleccionService service;

    public TipoEleccionRestController(TipoEleccionService service) {
        this.service = service;
    }

    @GetMapping
    public List<TipoEleccionDTO> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public TipoEleccionDTO obtener(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    public ResponseEntity<TipoEleccionDTO> crear(@RequestBody TipoEleccionDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(dto));
    }

    @PutMapping("/{id}")
    public TipoEleccionDTO actualizar(@PathVariable Long id, @RequestBody TipoEleccionDTO dto) {
        return service.actualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
