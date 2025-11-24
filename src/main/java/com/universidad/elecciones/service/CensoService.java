package com.universidad.elecciones.service;

import com.universidad.elecciones.dto.CensoRequestDTO;
import com.universidad.elecciones.dto.CensoResponseDTO;

import java.util.List;

public interface CensoService {

    List<CensoResponseDTO> listarPorEleccion(Long eleccionId);

    CensoResponseDTO agregar(CensoRequestDTO dto);

    void eliminar(Long id);
}
