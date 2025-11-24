package com.universidad.elecciones.service;

import com.universidad.elecciones.dto.ProgramaDTO;

import java.util.List;

public interface ProgramaService {

    List<ProgramaDTO> listar();

    List<ProgramaDTO> listarPorFacultad(Long facultadId);

    ProgramaDTO buscarPorId(Long id);

    ProgramaDTO crear(ProgramaDTO dto);

    ProgramaDTO actualizar(Long id, ProgramaDTO dto);

    void eliminar(Long id);
}
