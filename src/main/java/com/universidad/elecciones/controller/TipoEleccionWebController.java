package com.universidad.elecciones.controller;

import com.universidad.elecciones.dto.TipoEleccionDTO;
import com.universidad.elecciones.service.TipoEleccionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tipo-eleccion")
public class TipoEleccionWebController {

    private final TipoEleccionService service;

    public TipoEleccionWebController(TipoEleccionService service) {
        this.service = service;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("lista", service.listar());
        return "tipoEleccion/lista";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("tipoEleccion", new TipoEleccionDTO());
        return "tipoEleccion/form";
    }

    @PostMapping
    public String guardar(@ModelAttribute("tipoEleccion") TipoEleccionDTO dto) {
        if (dto.getId() == null) {
            service.crear(dto);
        } else {
            service.actualizar(dto.getId(), dto);
        }
        return "redirect:/tipo-eleccion";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("tipoEleccion", service.buscarPorId(id));
        return "tipoEleccion/form";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return "redirect:/tipo-eleccion";
    }
}
