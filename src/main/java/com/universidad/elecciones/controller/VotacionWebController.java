package com.universidad.elecciones.controller;

import com.universidad.elecciones.dto.VotoRequestDTO;
import com.universidad.elecciones.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/votar")
public class VotacionWebController {

    private final EleccionService eleccionService;
    private final CensoService censoService;
    private final InscripcionService inscripcionService;
    private final ResultadoService resultadoService;

    public VotacionWebController(EleccionService eleccionService,
                                 CensoService censoService,
                                 InscripcionService inscripcionService,
                                 ResultadoService resultadoService) {
        this.eleccionService = eleccionService;
        this.censoService = censoService;
        this.inscripcionService = inscripcionService;
        this.resultadoService = resultadoService;
    }

    @GetMapping("/{eleccionId}")
    public String pantallaVotacion(@PathVariable Long eleccionId, Model model) {

        model.addAttribute("eleccion", eleccionService.buscarPorId(eleccionId));
        model.addAttribute("candidatos", inscripcionService.listarPorEleccion(eleccionId));

        VotoRequestDTO voto = new VotoRequestDTO();
        voto.setEleccionId(eleccionId); // ← IMPORTANTE!
        model.addAttribute("voto", voto);
        
        System.out.print("bbbbbbbbbbbbbbbbbbbbbbb");

        return "votacion/votar";
    }



    @PostMapping
    public String votar(VotoRequestDTO dto, Model model) {
    	System.out.print("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");

        // 🔍 DEBUG PARA SABER QUÉ LLEGA
        model.addAttribute("debug",
                "eleccionId=" + dto.getEleccionId() +
                " | candidatoId=" + dto.getCandidatoId() +
                " | votanteId=" + dto.getVotanteId()
        );
        
        System.out.print(dto);

        System.out.print(dto.getCandidatoId());
        

        try {
            String mensaje = resultadoService.registrarVoto(dto, "127.0.0.1");
            model.addAttribute("mensaje", mensaje);
        } catch (Exception ex) {
            model.addAttribute("mensaje", ex.getMessage());
            
        }

        model.addAttribute("eleccion", eleccionService.buscarPorId(dto.getEleccionId()));
        model.addAttribute("candidatos", inscripcionService.listarPorEleccion(dto.getEleccionId()));

        return "votacion/votar";
    }

}
