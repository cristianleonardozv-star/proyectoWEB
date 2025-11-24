package com.universidad.elecciones.service.impl;

import com.universidad.elecciones.dto.InscripcionRequestDTO;
import com.universidad.elecciones.dto.InscripcionResponseDTO;
import com.universidad.elecciones.entity.*;
import com.universidad.elecciones.repository.*;
import com.universidad.elecciones.service.InscripcionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InscripcionServiceImpl implements InscripcionService {

    private final InscripcionRepository repo;
    private final EleccionRepository eleccionRepo;
    private final CandidatoRepository candidatoRepo;

    public InscripcionServiceImpl(InscripcionRepository repo,
                                  EleccionRepository eleccionRepo,
                                  CandidatoRepository candidatoRepo) {
        this.repo = repo;
        this.eleccionRepo = eleccionRepo;
        this.candidatoRepo = candidatoRepo;
    }

    private InscripcionResponseDTO map(Inscripcion i) {
        InscripcionResponseDTO dto = new InscripcionResponseDTO();
        dto.setId(i.getId());
        dto.setCandidatoNombre(i.getCandidato().getNombre());
        dto.setCandidatoDocumento(i.getCandidato().getDocumento());
        dto.setEstado(i.getEstado().name());
        dto.setNumeroTarjeton(i.getNumeroTarjeton());
        return dto;
    }

    @Override
    public List<InscripcionResponseDTO> listarPorEleccion(Long eleccionId) {
        return repo.findByEleccionId(eleccionId)
                .stream()
                .map(this::map)
                .collect(Collectors.toList());
    }

    @Override
    public InscripcionResponseDTO inscribir(InscripcionRequestDTO dto) {

        if (repo.existsByEleccionIdAndCandidatoId(dto.getEleccionId(), dto.getCandidatoId())) {
            throw new RuntimeException("El candidato ya está inscrito en esta elección");
        }

        Eleccion e = eleccionRepo.findById(dto.getEleccionId())
                .orElseThrow(() -> new RuntimeException("Elección no encontrada"));

        Candidato c = candidatoRepo.findById(dto.getCandidatoId())
                .orElseThrow(() -> new RuntimeException("Candidato no encontrado"));

        Inscripcion i = new Inscripcion();
        i.setEleccion(e);
        i.setCandidato(c);
        i.setNumeroTarjeton(dto.getNumeroTarjeton());

        return map(repo.save(i));
    }

    @Override
    public InscripcionResponseDTO aprobar(Long id) {
        Inscripcion i = repo.findById(id).orElseThrow();
        i.setEstado(EstadoInscripcion.APROBADO);
        return map(repo.save(i));
    }

    @Override
    public InscripcionResponseDTO rechazar(Long id) {
        Inscripcion i = repo.findById(id).orElseThrow();
        i.setEstado(EstadoInscripcion.RECHAZADO);
        return map(repo.save(i));
    }

    @Override
    public void eliminar(Long id) {
        repo.deleteById(id);
    }
}
