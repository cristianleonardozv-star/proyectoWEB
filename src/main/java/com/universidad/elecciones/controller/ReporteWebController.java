package com.universidad.elecciones.controller;

import com.universidad.elecciones.service.EleccionService;
import com.universidad.elecciones.service.ReporteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/reportes")
public class ReporteWebController {

    private final ReporteService reporteService;
    private final EleccionService eleccionService;

    public ReporteWebController(ReporteService reporteService,
                                EleccionService eleccionService) {
        this.reporteService = reporteService;
        this.eleccionService = eleccionService;
    }

    @GetMapping("/elecciones/{eleccionId}/resultados")
    public String resultadosEleccion(@PathVariable Long eleccionId, Model model) {

        model.addAttribute("eleccion", eleccionService.buscarPorId(eleccionId));
        model.addAttribute("resultados", reporteService.resultadosPorEleccion(eleccionId));

        return "reporte/resultados_eleccion";
    }
}
