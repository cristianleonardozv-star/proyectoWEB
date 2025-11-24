package com.universidad.elecciones.controller;

import com.universidad.elecciones.dto.ProgramaDTO;
import com.universidad.elecciones.service.FacultadService;
import com.universidad.elecciones.service.ProgramaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/programas")
public class ProgramaWebController {

    private final ProgramaService programaService;
    private final FacultadService facultadService;

    public ProgramaWebController(ProgramaService programaService,
                                 FacultadService facultadService) {
        this.programaService = programaService;
        this.facultadService = facultadService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("lista", programaService.listar());
        return "programa/lista";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("programa", new ProgramaDTO());
        model.addAttribute("facultades", facultadService.listar());
        return "programa/form";
    }

    @PostMapping
    public String guardar(@ModelAttribute("programa") ProgramaDTO dto) {
        if (dto.getId() == null) {
            programaService.crear(dto);
        } else {
            programaService.actualizar(dto.getId(), dto);
        }
        return "redirect:/programas";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("programa", programaService.buscarPorId(id));
        model.addAttribute("facultades", facultadService.listar());
        return "programa/form";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        programaService.eliminar(id);
        return "redirect:/programas";
    }
}
