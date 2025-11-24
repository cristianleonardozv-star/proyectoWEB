package com.universidad.elecciones.service;

import com.universidad.elecciones.dto.ProcesoDTO;
import java.util.List;

public interface ProcesoService {

    List<ProcesoDTO> listar();

    ProcesoDTO buscarPorId(Long id);

    ProcesoDTO crear(ProcesoDTO dto);

    ProcesoDTO actualizar(Long id, ProcesoDTO dto);

    void eliminar(Long id);
}
