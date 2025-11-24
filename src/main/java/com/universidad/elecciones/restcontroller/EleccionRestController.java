package com.universidad.elecciones.restcontroller;

import com.universidad.elecciones.dto.EleccionRequestDTO;
import com.universidad.elecciones.dto.EleccionResponseDTO;
import com.universidad.elecciones.service.EleccionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/elecciones")
@CrossOrigin(origins = "*")
public class EleccionRestController {

    private final EleccionService service;

    public EleccionRestController(EleccionService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<EleccionResponseDTO> crear(@RequestBody EleccionRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(dto));
    }

    @GetMapping
    public List<EleccionResponseDTO> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public EleccionResponseDTO obtener(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping("/{id}/abrir")
    public EleccionResponseDTO abrir(@PathVariable Long id) {
        return service.abrir(id);
    }

    @PostMapping("/{id}/cerrar")
    public EleccionResponseDTO cerrar(@PathVariable Long id) {
        return service.cerrar(id);
    }
}
