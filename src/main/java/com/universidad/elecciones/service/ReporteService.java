package com.universidad.elecciones.service;

import com.universidad.elecciones.dto.ResultadoVotoDTO;

import java.util.List;

public interface ReporteService {

    List<ResultadoVotoDTO> resultadosPorEleccion(Long eleccionId);
}

