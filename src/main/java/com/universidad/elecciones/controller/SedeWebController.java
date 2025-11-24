package com.universidad.elecciones.controller;

import com.universidad.elecciones.dto.SedeDTO;
import com.universidad.elecciones.service.SedeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/sedes")
public class SedeWebController {

    private final SedeService sedeService;

    public SedeWebController(SedeService sedeService) {
        this.sedeService = sedeService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("lista", sedeService.listar());
        return "sede/lista";
    }

    @GetMapping("/nuevo")
    public String formularioNuevo(Model model) {
        model.addAttribute("sede", new SedeDTO());
        return "sede/form";
    }

    @PostMapping
    public String guardar(@ModelAttribute("sede") SedeDTO dto) {
        if (dto.getId() == null) {
            sedeService.crear(dto);
        } else {
            sedeService.actualizar(dto.getId(), dto);
        }
        return "redirect:/sedes";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("sede", sedeService.buscarPorId(id));
        return "sede/form";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        sedeService.eliminar(id);
        return "redirect:/sedes";
    }
}
