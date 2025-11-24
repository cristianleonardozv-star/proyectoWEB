package com.universidad.elecciones.service;

import com.universidad.elecciones.dto.CandidatoDTO;

import java.util.List;

public interface CandidatoService {

    List<CandidatoDTO> listar();

    CandidatoDTO buscarPorId(Long id);

    CandidatoDTO crear(CandidatoDTO dto);

    CandidatoDTO actualizar(Long id, CandidatoDTO dto);

    void eliminar(Long id);
}
