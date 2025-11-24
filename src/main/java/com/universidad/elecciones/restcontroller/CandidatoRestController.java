package com.universidad.elecciones.restcontroller;

import com.universidad.elecciones.dto.CandidatoDTO;
import com.universidad.elecciones.service.CandidatoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/candidatos")
@CrossOrigin(origins = "*")
public class CandidatoRestController {

    private final CandidatoService service;

    public CandidatoRestController(CandidatoService service) {
        this.service = service;
    }

    @GetMapping
    public List<CandidatoDTO> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public CandidatoDTO obtener(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    public ResponseEntity<CandidatoDTO> crear(@RequestBody CandidatoDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(dto));
    }

    @PutMapping("/{id}")
    public CandidatoDTO actualizar(@PathVariable Long id, @RequestBody CandidatoDTO dto) {
        return service.actualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
