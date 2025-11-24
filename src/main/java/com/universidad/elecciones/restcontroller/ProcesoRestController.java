package com.universidad.elecciones.restcontroller;

import com.universidad.elecciones.dto.ProcesoDTO;
import com.universidad.elecciones.service.ProcesoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/procesos")
@CrossOrigin(origins = "*")
public class ProcesoRestController {

    private final ProcesoService service;

    public ProcesoRestController(ProcesoService service) {
        this.service = service;
    }

    @GetMapping
    public List<ProcesoDTO> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ProcesoDTO obtener(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    public ResponseEntity<ProcesoDTO> crear(@RequestBody ProcesoDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(dto));
    }

    @PutMapping("/{id}")
    public ProcesoDTO actualizar(@PathVariable Long id, @RequestBody ProcesoDTO dto) {
        return service.actualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
