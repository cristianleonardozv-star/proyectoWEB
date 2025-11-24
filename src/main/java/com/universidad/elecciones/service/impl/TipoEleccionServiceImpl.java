package com.universidad.elecciones.service.impl;

import com.universidad.elecciones.dto.TipoEleccionDTO;
import com.universidad.elecciones.entity.TipoEleccion;
import com.universidad.elecciones.repository.TipoEleccionRepository;
import com.universidad.elecciones.service.TipoEleccionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TipoEleccionServiceImpl implements TipoEleccionService {

    private final TipoEleccionRepository repo;

    public TipoEleccionServiceImpl(TipoEleccionRepository repo) {
        this.repo = repo;
    }

    private TipoEleccionDTO map(TipoEleccion t) {
        TipoEleccionDTO dto = new TipoEleccionDTO();
        dto.setId(t.getId());
        dto.setNombre(t.getNombre());
        return dto;
    }

    private TipoEleccion map(TipoEleccionDTO dto) {
        TipoEleccion t = new TipoEleccion();
        t.setId(dto.getId());
        t.setNombre(dto.getNombre());
        return t;
    }

    @Override
    public List<TipoEleccionDTO> listar() {
        return repo.findAll().stream().map(this::map).collect(Collectors.toList());
    }

    @Override
    public TipoEleccionDTO buscarPorId(Long id) {
        return map(repo.findById(id)
                .orElseThrow(() -> new RuntimeException("TipoEleccion no encontrado")));
    }

    @Override
    public TipoEleccionDTO crear(TipoEleccionDTO dto) {
        return map(repo.save(map(dto)));
    }

    @Override
    public TipoEleccionDTO actualizar(Long id, TipoEleccionDTO dto) {
        TipoEleccion t = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("TipoEleccion no encontrado"));
        t.setNombre(dto.getNombre());
        return map(repo.save(t));
    }

    @Override
    public void eliminar(Long id) {
        repo.deleteById(id);
    }
}
