package com.universidad.elecciones.service.impl;

import com.universidad.elecciones.dto.CensoRequestDTO;
import com.universidad.elecciones.dto.CensoResponseDTO;
import com.universidad.elecciones.entity.Censo;
import com.universidad.elecciones.entity.Eleccion;
import com.universidad.elecciones.entity.Votante;
import com.universidad.elecciones.repository.CensoRepository;
import com.universidad.elecciones.repository.EleccionRepository;
import com.universidad.elecciones.repository.VotanteRepository;
import com.universidad.elecciones.service.CensoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CensoServiceImpl implements CensoService {

    private final CensoRepository censoRepo;
    private final EleccionRepository eleccionRepo;
    private final VotanteRepository votanteRepo;

    public CensoServiceImpl(CensoRepository censoRepo,
                            EleccionRepository eleccionRepo,
                            VotanteRepository votanteRepo) {
        this.censoRepo = censoRepo;
        this.eleccionRepo = eleccionRepo;
        this.votanteRepo = votanteRepo;
    }

    private CensoResponseDTO map(Censo c) {
        CensoResponseDTO dto = new CensoResponseDTO();
        dto.setId(c.getId());
        dto.setVotanteDocumento(c.getVotante().getDocumento());
        dto.setVotanteNombre(c.getVotante().getNombre());
        return dto;
    }

    @Override
    public List<CensoResponseDTO> listarPorEleccion(Long eleccionId) {
        return censoRepo.findByEleccionId(eleccionId)
                .stream()
                .map(this::map)
                .collect(Collectors.toList());
    }

    @Override
    public CensoResponseDTO agregar(CensoRequestDTO dto) {

        if (censoRepo.existsByEleccionIdAndVotanteId(dto.getEleccionId(), dto.getVotanteId())) {
            throw new RuntimeException("El votante ya está en el censo");
        }

        Eleccion eleccion = eleccionRepo.findById(dto.getEleccionId())
                .orElseThrow(() -> new RuntimeException("Elección no encontrada"));

        Votante votante = votanteRepo.findById(dto.getVotanteId())
                .orElseThrow(() -> new RuntimeException("Votante no encontrado"));

        Censo c = new Censo();
        c.setEleccion(eleccion);
        c.setVotante(votante);

        return map(censoRepo.save(c));
    }

    @Override
    public void eliminar(Long id) {
        censoRepo.deleteById(id);
    }
}
