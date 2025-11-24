package com.universidad.elecciones.restcontroller;

import com.universidad.elecciones.dto.VotanteDTO;
import com.universidad.elecciones.service.VotanteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/votantes")
@CrossOrigin(origins = "*")
public class VotanteRestController {

    private final VotanteService service;

    public VotanteRestController(VotanteService service) {
        this.service = service;
    }

    @GetMapping
    public List<VotanteDTO> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public VotanteDTO obtener(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    public ResponseEntity<VotanteDTO> crear(@RequestBody VotanteDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(dto));
    }

    @PutMapping("/{id}")
    public VotanteDTO actualizar(@PathVariable Long id, @RequestBody VotanteDTO dto) {
        return service.actualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
