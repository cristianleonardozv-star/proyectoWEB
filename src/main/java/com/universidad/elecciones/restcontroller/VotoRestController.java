package com.universidad.elecciones.restcontroller;

import com.universidad.elecciones.dto.VotoRequestDTO;
import com.universidad.elecciones.service.VotoService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/votos")
@CrossOrigin(origins = "*")
public class VotoRestController {

    private final VotoService service;

    public VotoRestController(VotoService service) {
        this.service = service;
    }

    @PostMapping
    public String votar(@RequestBody VotoRequestDTO dto) {
        return service.votar(dto);
    }
}
