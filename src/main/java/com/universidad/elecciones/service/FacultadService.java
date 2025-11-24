package com.universidad.elecciones.service;

import com.universidad.elecciones.dto.FacultadDTO;

import java.util.List;

public interface FacultadService {

    List<FacultadDTO> listar();

    FacultadDTO buscarPorId(Long id);

    FacultadDTO crear(FacultadDTO dto);

    FacultadDTO actualizar(Long id, FacultadDTO dto);

    void eliminar(Long id);
}
