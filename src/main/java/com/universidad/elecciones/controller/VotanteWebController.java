package com.universidad.elecciones.controller;

import com.universidad.elecciones.dto.VotanteDTO;
import com.universidad.elecciones.service.VotanteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/votantes")
public class VotanteWebController {

    private final VotanteService service;

    public VotanteWebController(VotanteService service) {
        this.service = service;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("lista", service.listar());
        return "votante/lista";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("votante", new VotanteDTO());
        return "votante/form";
    }

    @PostMapping
    public String guardar(VotanteDTO dto) {
        if (dto.getId() == null)
            service.crear(dto);
        else
            service.actualizar(dto.getId(), dto);

        return "redirect:/votantes";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("votante", service.buscarPorId(id));
        return "votante/form";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return "redirect:/votantes";
    }
}

