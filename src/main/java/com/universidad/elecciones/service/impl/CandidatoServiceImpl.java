package com.universidad.elecciones.service.impl;

import com.universidad.elecciones.dto.CandidatoDTO;
import com.universidad.elecciones.entity.Candidato;
import com.universidad.elecciones.repository.CandidatoRepository;
import com.universidad.elecciones.service.CandidatoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CandidatoServiceImpl implements CandidatoService {

    private final CandidatoRepository repo;

    public CandidatoServiceImpl(CandidatoRepository repo) {
        this.repo = repo;
    }

    private CandidatoDTO map(Candidato c) {
        CandidatoDTO dto = new CandidatoDTO();
        dto.setId(c.getId());
        dto.setDocumento(c.getDocumento());
        dto.setNombre(c.getNombre());
        dto.setFoto(c.getFoto());
        dto.setPlanGobierno(c.getPlanGobierno());
        return dto;
    }

    private Candidato map(CandidatoDTO dto) {
        Candidato c = new Candidato();
        c.setId(dto.getId());
        c.setDocumento(dto.getDocumento());
        c.setNombre(dto.getNombre());
        c.setFoto(dto.getFoto());
        c.setPlanGobierno(dto.getPlanGobierno());
        return c;
    }

    @Override
    public List<CandidatoDTO> listar() {
        return repo.findAll()
                .stream()
                .map(this::map)
                .collect(Collectors.toList());
    }

    @Override
    public CandidatoDTO buscarPorId(Long id) {
        Candidato c = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Candidato no encontrado"));
        return map(c);
    }

    @Override
    public CandidatoDTO crear(CandidatoDTO dto) {
        return map(repo.save(map(dto)));
    }

    @Override
    public CandidatoDTO actualizar(Long id, CandidatoDTO dto) {
        Candidato c = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Candidato no encontrado"));

        c.setDocumento(dto.getDocumento());
        c.setNombre(dto.getNombre());
        c.setFoto(dto.getFoto());
        c.setPlanGobierno(dto.getPlanGobierno());

        return map(repo.save(c));
    }

    @Override
    public void eliminar(Long id) {
        repo.deleteById(id);
    }
}
