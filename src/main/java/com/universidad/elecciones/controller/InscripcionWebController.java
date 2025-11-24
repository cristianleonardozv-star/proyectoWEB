package com.universidad.elecciones.controller;

import com.universidad.elecciones.dto.InscripcionRequestDTO;
import com.universidad.elecciones.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/inscripciones")
public class InscripcionWebController {

    private final InscripcionService inscripcionService;
    private final EleccionService eleccionService;
    private final CandidatoService candidatoService;

    public InscripcionWebController(InscripcionService inscripcionService,
                                    EleccionService eleccionService,
                                    CandidatoService candidatoService) {
        this.inscripcionService = inscripcionService;
        this.eleccionService = eleccionService;
        this.candidatoService = candidatoService;
    }

    @GetMapping("/{eleccionId}")
    public String listar(@PathVariable Long eleccionId, Model model) {
        model.addAttribute("eleccion", eleccionService.buscarPorId(eleccionId));
        model.addAttribute("lista", inscripcionService.listarPorEleccion(eleccionId));
        model.addAttribute("candidatos", candidatoService.listar());
        model.addAttribute("inscribir", new InscripcionRequestDTO());
        return "inscripcion/lista";
    }

    @PostMapping
    public String guardar(InscripcionRequestDTO dto) {
        inscripcionService.inscribir(dto);
        return "redirect:/inscripciones/" + dto.getEleccionId();
    }

    @GetMapping("/{eleccionId}/aprobar/{id}")
    public String aprobar(@PathVariable Long eleccionId, @PathVariable Long id) {
        inscripcionService.aprobar(id);
        return "redirect:/inscripciones/" + eleccionId;
    }

    @GetMapping("/{eleccionId}/rechazar/{id}")
    public String rechazar(@PathVariable Long eleccionId, @PathVariable Long id) {
        inscripcionService.rechazar(id);
        return "redirect:/inscripciones/" + eleccionId;
    }

    @GetMapping("/{eleccionId}/eliminar/{id}")
    public String eliminar(@PathVariable Long eleccionId, @PathVariable Long id) {
        inscripcionService.eliminar(id);
        return "redirect:/inscripciones/" + eleccionId;
    }
}
