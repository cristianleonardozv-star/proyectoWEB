package com.universidad.elecciones.service.impl;

import com.universidad.elecciones.dto.VotoRequestDTO;
import com.universidad.elecciones.entity.*;
import com.universidad.elecciones.repository.*;
import com.universidad.elecciones.service.VotoService;
import org.springframework.stereotype.Service;

@Service
public class VotoServiceImpl implements VotoService {

    private final VotoRepository votoRepo;
    private final EleccionRepository eleccionRepo;
    private final VotanteRepository votanteRepo;
    private final InscripcionRepository inscripcionRepo;
    private final CensoRepository censoRepo;

    public VotoServiceImpl(VotoRepository votoRepo,
                           EleccionRepository eleccionRepo,
                           VotanteRepository votanteRepo,
                           InscripcionRepository inscripcionRepo,
                           CensoRepository censoRepo) {
        this.votoRepo = votoRepo;
        this.eleccionRepo = eleccionRepo;
        this.votanteRepo = votanteRepo;
        this.inscripcionRepo = inscripcionRepo;
        this.censoRepo = censoRepo;
    }

    @Override
    public String votar(VotoRequestDTO dto) {

        Eleccion eleccion = eleccionRepo.findById(dto.getEleccionId())
                .orElseThrow(() -> new RuntimeException("Elección no encontrada"));

        if (eleccion.getEstado() != EstadoEleccion.ABIERTA)
            throw new RuntimeException("La elección no está abierta");

        if (!censoRepo.existsByEleccionIdAndVotanteId(dto.getEleccionId(), dto.getVotanteId()))
            throw new RuntimeException("El votante NO pertenece al censo");

        if (votoRepo.existsByEleccionIdAndVotanteId(dto.getEleccionId(), dto.getVotanteId()))
            throw new RuntimeException("El votante YA votó");

        Inscripcion ins = inscripcionRepo.findByEleccionId(dto.getEleccionId())
                .stream()
                .filter(i -> i.getCandidato().getId().equals(dto.getCandidatoId()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Candidato no inscrito"));

        if (ins.getEstado() != EstadoInscripcion.APROBADO)
            throw new RuntimeException("Candidato no aprobado");

        // Guardar voto
        Votante votante = votanteRepo.findById(dto.getVotanteId())
                .orElseThrow(() -> new RuntimeException("Votante no encontrado"));

        Voto voto = new Voto();
        voto.setEleccion(eleccion);
        voto.setVotante(votante);
        voto.setCandidato(ins.getCandidato());

        votoRepo.save(voto);

        return "Voto registrado correctamente";
    }

    @Override
    public long contarVotos(Long eleccionId, Long candidatoId) {
        return votoRepo.countByEleccionIdAndCandidatoId(eleccionId, candidatoId);
    }
}
