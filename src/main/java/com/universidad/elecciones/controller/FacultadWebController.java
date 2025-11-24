package com.universidad.elecciones.controller;

import com.universidad.elecciones.dto.FacultadDTO;
import com.universidad.elecciones.service.FacultadService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/facultades")
public class FacultadWebController {

    private final FacultadService facultadService;

    public FacultadWebController(FacultadService facultadService) {
        this.facultadService = facultadService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("lista", facultadService.listar());
        return "facultad/lista";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("facultad", new FacultadDTO());
        return "facultad/form";
    }

    @PostMapping
    public String guardar(@ModelAttribute("facultad") FacultadDTO dto) {
        if (dto.getId() == null) {
            facultadService.crear(dto);
        } else {
            facultadService.actualizar(dto.getId(), dto);
        }
        return "redirect:/facultades";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("facultad", facultadService.buscarPorId(id));
        return "facultad/form";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        facultadService.eliminar(id);
        return "redirect:/facultades";
    }
}
