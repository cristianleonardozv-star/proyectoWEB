package com.universidad.elecciones.service.impl;

import com.universidad.elecciones.dto.SedeDTO;
import com.universidad.elecciones.entity.Sede;
import com.universidad.elecciones.repository.SedeRepository;
import com.universidad.elecciones.service.SedeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SedeServiceImpl implements SedeService {

    private final SedeRepository sedeRepository;

    public SedeServiceImpl(SedeRepository sedeRepository) {
        this.sedeRepository = sedeRepository;
    }

    private SedeDTO mapToDTO(Sede entity) {
        SedeDTO dto = new SedeDTO();
        dto.setId(entity.getId());
        dto.setNombre(entity.getNombre());
        return dto;
    }

    private Sede mapToEntity(SedeDTO dto) {
        Sede s = new Sede();
        s.setId(dto.getId());
        s.setNombre(dto.getNombre());
        return s;
    }

    @Override
    public List<SedeDTO> listar() {
        return sedeRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public SedeDTO buscarPorId(Long id) {
        Sede s = sedeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sede no encontrada"));
        return mapToDTO(s);
    }

    @Override
    public SedeDTO crear(SedeDTO dto) {
        Sede nueva = sedeRepository.save(mapToEntity(dto));
        return mapToDTO(nueva);
    }

    @Override
    public SedeDTO actualizar(Long id, SedeDTO dto) {
        Sede s = sedeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sede no encontrada"));
        s.setNombre(dto.getNombre());
        return mapToDTO(sedeRepository.save(s));
    }

    @Override
    public void eliminar(Long id) {
        sedeRepository.deleteById(id);
    }
}

