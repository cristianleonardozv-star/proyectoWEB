package com.universidad.elecciones.dto;

import lombok.Data;

@Data
public class InscripcionRequestDTO {
    private Long eleccionId;
    private Long candidatoId;
    private Integer numeroTarjeton;
}
