package com.universidad.elecciones.service;

import com.universidad.elecciones.dto.EleccionRequestDTO;
import com.universidad.elecciones.dto.EleccionResponseDTO;

import java.util.List;

public interface EleccionService {

    EleccionResponseDTO crear(EleccionRequestDTO dto);

    List<EleccionResponseDTO> listar();

    EleccionResponseDTO buscarPorId(Long id);

    EleccionResponseDTO abrir(Long id);

    EleccionResponseDTO cerrar(Long id);
}

