package com.universidad.elecciones.restcontroller;

import com.universidad.elecciones.dto.InscripcionRequestDTO;
import com.universidad.elecciones.dto.InscripcionResponseDTO;
import com.universidad.elecciones.service.InscripcionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inscripciones")
@CrossOrigin(origins = "*")
public class InscripcionRestController {

    private final InscripcionService service;

    public InscripcionRestController(InscripcionService service) {
        this.service = service;
    }

    @GetMapping("/{eleccionId}")
    public List<InscripcionResponseDTO> listar(@PathVariable Long eleccionId) {
        return service.listarPorEleccion(eleccionId);
    }

    @PostMapping
    public InscripcionResponseDTO inscribir(@RequestBody InscripcionRequestDTO dto) {
        return service.inscribir(dto);
    }

    @PostMapping("/{id}/aprobar")
    public InscripcionResponseDTO aprobar(@PathVariable Long id) {
        return service.aprobar(id);
    }

    @PostMapping("/{id}/rechazar")
    public InscripcionResponseDTO rechazar(@PathVariable Long id) {
        return service.rechazar(id);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}
