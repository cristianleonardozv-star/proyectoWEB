package com.universidad.elecciones.restcontroller;

import com.universidad.elecciones.dto.ResultadoVotoDTO;
import com.universidad.elecciones.service.ReporteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reportes")
@CrossOrigin(origins = "*")
public class ReporteRestController {

    private final ReporteService reporteService;

    public ReporteRestController(ReporteService reporteService) {
        this.reporteService = reporteService;
    }

    @GetMapping("/elecciones/{eleccionId}/resultados")
    public List<ResultadoVotoDTO> resultados(@PathVariable Long eleccionId) {
        return reporteService.resultadosPorEleccion(eleccionId);
    }
}
