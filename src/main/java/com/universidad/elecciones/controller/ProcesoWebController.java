package com.universidad.elecciones.controller;

import com.universidad.elecciones.dto.ProcesoDTO;
import com.universidad.elecciones.service.ProcesoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/procesos")
public class ProcesoWebController {

    private final ProcesoService service;

    public ProcesoWebController(ProcesoService service) {
        this.service = service;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("lista", service.listar());
        return "proceso/lista";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("proceso", new ProcesoDTO());
        return "proceso/form";
    }

    @PostMapping
    public String guardar(@ModelAttribute("proceso") ProcesoDTO dto) {
        if (dto.getId() == null) {
            service.crear(dto);
        } else {
            service.actualizar(dto.getId(), dto);
        }
        return "redirect:/procesos";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("proceso", service.buscarPorId(id));
        return "proceso/form";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return "redirect:/procesos";
    }
}
