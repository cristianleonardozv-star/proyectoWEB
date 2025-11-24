package com.universidad.elecciones.dto;

import lombok.Data;

@Data
public class VotoRequestDTO {
    private Long eleccionId;
    private Long votanteId;
    private Long candidatoId;
}
