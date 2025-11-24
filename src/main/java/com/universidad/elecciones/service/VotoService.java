package com.universidad.elecciones.service;

import com.universidad.elecciones.dto.VotoRequestDTO;

public interface VotoService {

    String votar(VotoRequestDTO dto);

    long contarVotos(Long eleccionId, Long candidatoId);
}
