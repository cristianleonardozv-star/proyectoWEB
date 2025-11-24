package com.universidad.elecciones.service.impl;

import com.universidad.elecciones.dto.ProcesoDTO;
import com.universidad.elecciones.entity.Proceso;
import com.universidad.elecciones.repository.ProcesoRepository;
import com.universidad.elecciones.service.ProcesoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProcesoServiceImpl implements ProcesoService {

    private final ProcesoRepository repo;

    public ProcesoServiceImpl(ProcesoRepository repo) {
        this.repo = repo;
    }

    private ProcesoDTO map(Proceso p) {
        ProcesoDTO dto = new ProcesoDTO();
        dto.setId(p.getId());
        dto.setNombre(p.getNombre());
        return dto;
    }

    private Proceso map(ProcesoDTO dto) {
        Proceso p = new Proceso();
        p.setId(dto.getId());
        p.setNombre(dto.getNombre());
        return p;
    }

    @Override
    public List<ProcesoDTO> listar() {
        return repo.findAll().stream().map(this::map).collect(Collectors.toList());
    }

    @Override
    public ProcesoDTO buscarPorId(Long id) {
        return map(repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Proceso no encontrado")));
    }

    @Override
    public ProcesoDTO crear(ProcesoDTO dto) {
        return map(repo.save(map(dto)));
    }

    @Override
    public ProcesoDTO actualizar(Long id, ProcesoDTO dto) {
        Proceso p = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Proceso no encontrado"));

        p.setNombre(dto.getNombre());

        return map(repo.save(p));
    }

    @Override
    public void eliminar(Long id) {
        repo.deleteById(id);
    }
}
