package com.universidad.elecciones.service;

import com.universidad.elecciones.dto.TipoDTO;
import java.util.List;

public interface TipoService {

    List<TipoDTO> listar();

    TipoDTO buscarPorId(Long id);

    TipoDTO crear(TipoDTO dto);

    TipoDTO actualizar(Long id, TipoDTO dto);

    void eliminar(Long id);
}
