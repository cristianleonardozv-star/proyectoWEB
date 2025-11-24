package com.universidad.elecciones.service;

import com.universidad.elecciones.dto.VotoRequestDTO;
import java.util.List;

public interface ResultadoService {
    String registrarVoto(VotoRequestDTO dto, String ip);
}
