package com.universidad.elecciones.service.impl;

import com.universidad.elecciones.dto.VotanteDTO;
import com.universidad.elecciones.entity.Votante;
import com.universidad.elecciones.repository.VotanteRepository;
import com.universidad.elecciones.service.VotanteService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VotanteServiceImpl implements VotanteService {

    private final VotanteRepository repo;

    public VotanteServiceImpl(VotanteRepository repo) {
        this.repo = repo;
    }

    private VotanteDTO map(Votante v) {
        VotanteDTO dto = new VotanteDTO();
        dto.setId(v.getId());
        dto.setDocumento(v.getDocumento());
        dto.setNombre(v.getNombre());
        dto.setEmail(v.getEmail());
        dto.setTelefono(v.getTelefono());
        return dto;
    }

    private Votante map(VotanteDTO dto) {
        Votante v = new Votante();
        v.setId(dto.getId());
        v.setDocumento(dto.getDocumento());
        v.setNombre(dto.getNombre());
        v.setEmail(dto.getEmail());
        v.setTelefono(dto.getTelefono());
        return v;
    }

    @Override
    public List<VotanteDTO> listar() {
        return repo.findAll().stream().map(this::map).collect(Collectors.toList());
    }

    @Override
    public VotanteDTO buscarPorId(Long id) {
        return map(repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Votante no encontrado")));
    }

    @Override
    public VotanteDTO crear(VotanteDTO dto) {
        return map(repo.save(map(dto)));
    }

    @Override
    public VotanteDTO actualizar(Long id, VotanteDTO dto) {
        Votante v = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Votante no encontrado"));

        v.setDocumento(dto.getDocumento());
        v.setNombre(dto.getNombre());
        v.setEmail(dto.getEmail());
        v.setTelefono(dto.getTelefono());

        return map(repo.save(v));
    }

    @Override
    public void eliminar(Long id) {
        repo.deleteById(id);
    }
}
