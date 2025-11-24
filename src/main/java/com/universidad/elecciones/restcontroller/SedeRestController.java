package com.universidad.elecciones.restcontroller;

import com.universidad.elecciones.dto.SedeDTO;
import com.universidad.elecciones.service.SedeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sedes")
@CrossOrigin(origins = "*")
public class SedeRestController {

    private final SedeService sedeService;

    public SedeRestController(SedeService sedeService) {
        this.sedeService = sedeService;
    }

    @GetMapping
    public List<SedeDTO> listar() {
        return sedeService.listar();
    }

    @GetMapping("/{id}")
    public SedeDTO obtener(@PathVariable Long id) {
        return sedeService.buscarPorId(id);
    }

    @PostMapping
    public ResponseEntity<SedeDTO> crear(@RequestBody SedeDTO dto) {
        SedeDTO creada = sedeService.crear(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creada);
    }

    @PutMapping("/{id}")
    public SedeDTO actualizar(@PathVariable Long id, @RequestBody SedeDTO dto) {
        return sedeService.actualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        sedeService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
