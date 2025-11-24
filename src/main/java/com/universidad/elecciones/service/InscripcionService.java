package com.universidad.elecciones.service;

import com.universidad.elecciones.dto.InscripcionRequestDTO;
import com.universidad.elecciones.dto.InscripcionResponseDTO;

import java.util.List;

public interface InscripcionService {

    List<InscripcionResponseDTO> listarPorEleccion(Long eleccionId);

    InscripcionResponseDTO inscribir(InscripcionRequestDTO dto);

    InscripcionResponseDTO aprobar(Long id);

    InscripcionResponseDTO rechazar(Long id);

    void eliminar(Long id);
}
