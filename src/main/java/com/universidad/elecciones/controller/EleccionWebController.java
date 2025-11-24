package com.universidad.elecciones.controller;

import com.universidad.elecciones.dto.EleccionRequestDTO;
import com.universidad.elecciones.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/elecciones")
public class EleccionWebController {

    private final EleccionService eleccionService;
    private final FacultadService facultadService;
    private final ProgramaService programaService;
    private final SedeService sedeService;
    private final ProcesoService procesoService;
    private final TipoService tipoService;
    private final TipoEleccionService tipoEleccionService;

    public EleccionWebController(EleccionService eleccionService,
                                 FacultadService facultadService,
                                 ProgramaService programaService,
                                 SedeService sedeService,
                                 ProcesoService procesoService,
                                 TipoService tipoService,
                                 TipoEleccionService tipoEleccionService) {
        this.eleccionService = eleccionService;
        this.facultadService = facultadService;
        this.programaService = programaService;
        this.sedeService = sedeService;
        this.procesoService = procesoService;
        this.tipoService = tipoService;
        this.tipoEleccionService = tipoEleccionService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("lista", eleccionService.listar());
        return "eleccion/lista";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("eleccion", new EleccionRequestDTO());
        model.addAttribute("facultades", facultadService.listar());
        model.addAttribute("programas", programaService.listar());
        model.addAttribute("sedes", sedeService.listar());
        model.addAttribute("procesos", procesoService.listar());
        model.addAttribute("tipos", tipoService.listar());
        model.addAttribute("tiposEleccion", tipoEleccionService.listar());
        return "eleccion/form";
    }

    @PostMapping
    public String guardar(EleccionRequestDTO dto) {
        eleccionService.crear(dto);
        return "redirect:/elecciones";
    }

    @GetMapping("/{id}/abrir")
    public String abrir(@PathVariable Long id) {
        eleccionService.abrir(id);
        return "redirect:/elecciones";
    }

    @GetMapping("/{id}/cerrar")
    public String cerrar(@PathVariable Long id) {
        eleccionService.cerrar(id);
        return "redirect:/elecciones";
    }
}
