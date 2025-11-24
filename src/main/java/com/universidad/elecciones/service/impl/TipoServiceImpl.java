package com.universidad.elecciones.service.impl;

import com.universidad.elecciones.dto.TipoDTO;
import com.universidad.elecciones.entity.Tipo;
import com.universidad.elecciones.repository.TipoRepository;
import com.universidad.elecciones.service.TipoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TipoServiceImpl implements TipoService {

    private final TipoRepository repo;

    public TipoServiceImpl(TipoRepository repo) {
        this.repo = repo;
    }

    private TipoDTO map(Tipo t) {
        TipoDTO d = new TipoDTO();
        d.setId(t.getId());
        d.setNombre(t.getNombre());
        return d;
    }

    private Tipo map(TipoDTO dto) {
        Tipo t = new Tipo();
        t.setId(dto.getId());
        t.setNombre(dto.getNombre());
        return t;
    }

    @Override
    public List<TipoDTO> listar() {
        return repo.findAll().stream().map(this::map).collect(Collectors.toList());
    }

    @Override
    public TipoDTO buscarPorId(Long id) {
        Tipo t = repo.findById(id).orElseThrow(() -> new RuntimeException("Tipo no encontrado"));
        return map(t);
    }

    @Override
    public TipoDTO crear(TipoDTO dto) {
        return map(repo.save(map(dto)));
    }

    @Override
    public TipoDTO actualizar(Long id, TipoDTO dto) {
        Tipo t = repo.findById(id).orElseThrow(() -> new RuntimeException("Tipo no encontrado"));
        t.setNombre(dto.getNombre());
        return map(repo.save(t));
    }

    @Override
    public void eliminar(Long id) {
        repo.deleteById(id);
    }
}
