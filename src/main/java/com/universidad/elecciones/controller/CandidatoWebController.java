package com.universidad.elecciones.controller;

import com.universidad.elecciones.dto.CandidatoDTO;
import com.universidad.elecciones.service.CandidatoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/candidatos")
public class CandidatoWebController {

    private final CandidatoService service;

    public CandidatoWebController(CandidatoService service) {
        this.service = service;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("lista", service.listar());
        return "candidato/lista";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("candidato", new CandidatoDTO());
        return "candidato/form";
    }

    @PostMapping
    public String guardar(CandidatoDTO dto) {
        if (dto.getId() == null)
            service.crear(dto);
        else
            service.actualizar(dto.getId(), dto);

        return "redirect:/candidatos";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("candidato", service.buscarPorId(id));
        return "candidato/form";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return "redirect:/candidatos";
    }
}
