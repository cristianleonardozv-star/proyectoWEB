package com.universidad.elecciones.restcontroller;

import com.universidad.elecciones.dto.ProgramaDTO;
import com.universidad.elecciones.service.ProgramaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/programas")
@CrossOrigin(origins = "*")
public class ProgramaRestController {

    private final ProgramaService programaService;

    public ProgramaRestController(ProgramaService programaService) {
        this.programaService = programaService;
    }

    @GetMapping
    public List<ProgramaDTO> listar() {
        return programaService.listar();
    }

    @GetMapping("/facultad/{facultadId}")
    public List<ProgramaDTO> listarPorFacultad(@PathVariable Long facultadId) {
        return programaService.listarPorFacultad(facultadId);
    }

    @GetMapping("/{id}")
    public ProgramaDTO obtener(@PathVariable Long id) {
        return programaService.buscarPorId(id);
    }

    @PostMapping
    public ResponseEntity<ProgramaDTO> crear(@RequestBody ProgramaDTO dto) {
        ProgramaDTO creado = programaService.crear(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/{id}")
    public ProgramaDTO actualizar(@PathVariable Long id, @RequestBody ProgramaDTO dto) {
        return programaService.actualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        programaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
