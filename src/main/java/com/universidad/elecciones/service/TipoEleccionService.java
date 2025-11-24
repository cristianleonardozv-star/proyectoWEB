package com.universidad.elecciones.service;

import com.universidad.elecciones.dto.TipoEleccionDTO;
import java.util.List;

public interface TipoEleccionService {

    List<TipoEleccionDTO> listar();

    TipoEleccionDTO buscarPorId(Long id);

    TipoEleccionDTO crear(TipoEleccionDTO dto);

    TipoEleccionDTO actualizar(Long id, TipoEleccionDTO dto);

    void eliminar(Long id);
}
