package com.universidad.elecciones.service.impl;

import com.universidad.elecciones.dto.VotoRequestDTO;
import com.universidad.elecciones.entity.*;
import com.universidad.elecciones.repository.*;
import com.universidad.elecciones.service.ResultadoService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ResultadoServiceImpl implements ResultadoService {

    private final ResultadoRepository resultadoRepo;
    private final CensoRepository censoRepo;
    private final InscripcionRepository inscripcionRepo;
    private final EleccionRepository eleccionRepo;
    private final CandidatoRepository candidatoRepo;

    public ResultadoServiceImpl(ResultadoRepository resultadoRepo,
                                CensoRepository censoRepo,
                                InscripcionRepository inscripcionRepo,
                                EleccionRepository eleccionRepo,
                                CandidatoRepository candidatoRepo) {

        this.resultadoRepo = resultadoRepo;
        this.censoRepo = censoRepo;
        this.inscripcionRepo = inscripcionRepo;
        this.eleccionRepo = eleccionRepo;
        this.candidatoRepo = candidatoRepo;
    }

    
    public String registrarVoto(VotoRequestDTO dto, String ip) {

        Eleccion eleccion = eleccionRepo.findById(dto.getEleccionId())
                .orElseThrow();

        if (!"ABIERTA".equals(eleccion.getEstado()))
            throw new RuntimeException("La elección no está abierta");

        Censo censo = censoRepo.findByEleccionId(dto.getEleccionId())
                .stream()
                .filter(c -> c.getVotante().getId().equals(dto.getVotanteId()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("El votante no pertenece al censo"));

        if (resultadoRepo.existsByEleccionIdAndCensoId(dto.getEleccionId(), censo.getId()))
            throw new RuntimeException("El votante ya registró voto");

        Inscripcion inscripcion = inscripcionRepo.findByEleccionId(dto.getEleccionId())
                .stream()
                .filter(i -> i.getCandidato().getId().equals(dto.getCandidatoId()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Candidato no inscrito"));

        if (!"APROBADO".equals(inscripcion.getEstado()))
            throw new RuntimeException("Candidato no aprobado");

        Resultado r = new Resultado();
        r.setEleccion(eleccion);
        r.setCandidato(inscripcion.getCandidato());
        r.setCenso(censo);
        r.setDocumento(censo.getVotante().getDocumento());
        r.setIp(ip);
        r.setUuid(UUID.randomUUID().toString());
        r.setEstado("REGISTRADO");

        resultadoRepo.save(r);

        return "Voto registrado correctamente";
    }
}
