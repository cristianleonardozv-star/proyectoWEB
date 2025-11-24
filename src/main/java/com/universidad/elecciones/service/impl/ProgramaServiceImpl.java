package com.universidad.elecciones.service.impl;

import com.universidad.elecciones.dto.ProgramaDTO;
import com.universidad.elecciones.entity.Facultad;
import com.universidad.elecciones.entity.Programa;
import com.universidad.elecciones.repository.FacultadRepository;
import com.universidad.elecciones.repository.ProgramaRepository;
import com.universidad.elecciones.service.ProgramaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProgramaServiceImpl implements ProgramaService {

    private final ProgramaRepository programaRepository;
    private final FacultadRepository facultadRepository;

    public ProgramaServiceImpl(ProgramaRepository programaRepository,
                               FacultadRepository facultadRepository) {
        this.programaRepository = programaRepository;
        this.facultadRepository = facultadRepository;
    }

    private ProgramaDTO mapToDTO(Programa entidad) {
        ProgramaDTO dto = new ProgramaDTO();
        dto.setId(entidad.getId());
        dto.setNombre(entidad.getNombre());
        dto.setFacultadId(entidad.getFacultad().getId());
        dto.setFacultadNombre(entidad.getFacultad().getNombre());
        return dto;
    }

    @Override
    public List<ProgramaDTO> listar() {
        return programaRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProgramaDTO> listarPorFacultad(Long facultadId) {
        return programaRepository.findByFacultadId(facultadId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProgramaDTO buscarPorId(Long id) {
        Programa p = programaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Programa no encontrado"));
        return mapToDTO(p);
    }

    @Override
    public ProgramaDTO crear(ProgramaDTO dto) {
        Facultad facultad = facultadRepository.findById(dto.getFacultadId())
                .orElseThrow(() -> new RuntimeException("Facultad no encontrada"));

        Programa p = new Programa();
        p.setNombre(dto.getNombre());
        p.setFacultad(facultad);

        return mapToDTO(programaRepository.save(p));
    }

    @Override
    public ProgramaDTO actualizar(Long id, ProgramaDTO dto) {
        Programa p = programaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Programa no encontrado"));

        p.setNombre(dto.getNombre());

        if (dto.getFacultadId() != null && !dto.getFacultadId().equals(p.getFacultad().getId())) {
            Facultad facultad = facultadRepository.findById(dto.getFacultadId())
                    .orElseThrow(() -> new RuntimeException("Facultad no encontrada"));
            p.setFacultad(facultad);
        }

        return mapToDTO(programaRepository.save(p));
    }

    @Override
    public void eliminar(Long id) {
        programaRepository.deleteById(id);
    }
}
