package com.universidad.elecciones.service;

import com.universidad.elecciones.dto.SedeDTO;

import java.util.List;

public interface SedeService {

    List<SedeDTO> listar();

    SedeDTO buscarPorId(Long id);

    SedeDTO crear(SedeDTO dto);

    SedeDTO actualizar(Long id, SedeDTO dto);

    void eliminar(Long id);
}

