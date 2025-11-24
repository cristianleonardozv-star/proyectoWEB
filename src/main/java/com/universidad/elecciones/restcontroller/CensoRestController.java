package com.universidad.elecciones.restcontroller;

import com.universidad.elecciones.dto.CensoRequestDTO;
import com.universidad.elecciones.dto.CensoResponseDTO;
import com.universidad.elecciones.service.CensoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/censo")
@CrossOrigin(origins = "*")
public class CensoRestController {

    private final CensoService service;

    public CensoRestController(CensoService service) {
        this.service = service;
    }

    @GetMapping("/{eleccionId}")
    public List<CensoResponseDTO> listar(@PathVariable Long eleccionId) {
        return service.listarPorEleccion(eleccionId);
    }

    @PostMapping
    public CensoResponseDTO agregar(@RequestBody CensoRequestDTO dto) {
        return service.agregar(dto);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}
