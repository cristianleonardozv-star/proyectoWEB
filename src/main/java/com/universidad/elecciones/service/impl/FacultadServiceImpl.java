package com.universidad.elecciones.service.impl;

import com.universidad.elecciones.dto.FacultadDTO;
import com.universidad.elecciones.entity.Facultad;
import com.universidad.elecciones.repository.FacultadRepository;
import com.universidad.elecciones.service.FacultadService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class FacultadServiceImpl implements FacultadService {

    private final FacultadRepository facultadRepository;

    public FacultadServiceImpl(FacultadRepository facultadRepository) {
        this.facultadRepository = facultadRepository;
    }

    private FacultadDTO mapToDTO(Facultad entidad) {
        FacultadDTO dto = new FacultadDTO();
        dto.setId(entidad.getId());
        dto.setNombre(entidad.getNombre());
        return dto;
    }

    private Facultad mapToEntity(FacultadDTO dto) {
        Facultad f = new Facultad();
        f.setId(dto.getId());
        f.setNombre(dto.getNombre());
        return f;
    }

    @Override
    public List<FacultadDTO> listar() {
        return facultadRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public FacultadDTO buscarPorId(Long id) {
        Facultad f = facultadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Facultad no encontrada"));
        return mapToDTO(f);
    }

    @Override
    public FacultadDTO crear(FacultadDTO dto) {
        Facultad f = mapToEntity(dto);
        Facultad guardada = facultadRepository.save(f);
        return mapToDTO(guardada);
    }

    @Override
    public FacultadDTO actualizar(Long id, FacultadDTO dto) {
        Facultad f = facultadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Facultad no encontrada"));
        f.setNombre(dto.getNombre());
        return mapToDTO(facultadRepository.save(f));
    }

    @Override
    public void eliminar(Long id) {
        facultadRepository.deleteById(id);
    }
}
