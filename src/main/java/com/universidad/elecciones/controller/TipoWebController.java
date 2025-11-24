package com.universidad.elecciones.controller;

import com.universidad.elecciones.dto.TipoDTO;
import com.universidad.elecciones.service.TipoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tipos")
public class TipoWebController {

    private final TipoService service;

    public TipoWebController(TipoService service) {
        this.service = service;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("lista", service.listar());
        return "tipo/lista";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("tipo", new TipoDTO());
        return "tipo/form";
    }

    @PostMapping
    public String guardar(@ModelAttribute("tipo") TipoDTO dto) {
        if (dto.getId() == null) {
            service.crear(dto);
        } else {
            service.actualizar(dto.getId(), dto);
        }
        return "redirect:/tipos";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("tipo", service.buscarPorId(id));
        return "tipo/form";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return "redirect:/tipos";
    }
}
