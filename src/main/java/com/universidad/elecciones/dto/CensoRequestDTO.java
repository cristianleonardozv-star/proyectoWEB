package com.universidad.elecciones.dto;

import lombok.Data;

@Data
public class CensoRequestDTO {
    private Long eleccionId;
    private Long votanteId;
}
