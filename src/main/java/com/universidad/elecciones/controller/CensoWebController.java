package com.universidad.elecciones.controller;

import com.universidad.elecciones.dto.CensoRequestDTO;
import com.universidad.elecciones.service.CensoService;
import com.universidad.elecciones.service.EleccionService;
import com.universidad.elecciones.service.VotanteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/censo")
public class CensoWebController {

    private final CensoService censoService;
    private final EleccionService eleccionService;
    private final VotanteService votanteService;

    public CensoWebController(CensoService censoService,
                              EleccionService eleccionService,
                              VotanteService votanteService) {
        this.censoService = censoService;
        this.eleccionService = eleccionService;
        this.votanteService = votanteService;
    }

    @GetMapping("/{eleccionId}")
    public String listar(@PathVariable Long eleccionId, Model model) {
        model.addAttribute("eleccion", eleccionService.buscarPorId(eleccionId));
        model.addAttribute("censo", censoService.listarPorEleccion(eleccionId));
        model.addAttribute("votantes", votanteService.listar());
        model.addAttribute("censoRequest", new CensoRequestDTO());
        return "censo/lista";
    }

    @PostMapping
    public String agregar(CensoRequestDTO dto) {
        censoService.agregar(dto);
        return "redirect:/censo/" + dto.getEleccionId();
    }

    @GetMapping("/eliminar/{eleccionId}/{id}")
    public String eliminar(@PathVariable Long eleccionId, @PathVariable Long id) {
        censoService.eliminar(id);
        return "redirect:/censo/" + eleccionId;
    }
}

