package com.universidad.elecciones.restcontroller;

import com.universidad.elecciones.dto.FacultadDTO;
import com.universidad.elecciones.service.FacultadService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/facultades")
@CrossOrigin(origins = "*")
public class FacultadRestController {

    private final FacultadService facultadService;

    public FacultadRestController(FacultadService facultadService) {
        this.facultadService = facultadService;
    }

    @GetMapping
    public List<FacultadDTO> listar() {
        return facultadService.listar();
    }

    @GetMapping("/{id}")
    public FacultadDTO obtener(@PathVariable Long id) {
        return facultadService.buscarPorId(id);
    }

    @PostMapping
    public ResponseEntity<FacultadDTO> crear(@RequestBody FacultadDTO dto) {
        FacultadDTO creada = facultadService.crear(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creada);
    }

    @PutMapping("/{id}")
    public FacultadDTO actualizar(@PathVariable Long id, @RequestBody FacultadDTO dto) {
        return facultadService.actualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        facultadService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
