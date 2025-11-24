package com.universidad.elecciones.service;

import com.universidad.elecciones.dto.VotanteDTO;

import java.util.List;

public interface VotanteService {

    List<VotanteDTO> listar();

    VotanteDTO buscarPorId(Long id);

    VotanteDTO crear(VotanteDTO dto);

    VotanteDTO actualizar(Long id, VotanteDTO dto);

    void eliminar(Long id);
}
