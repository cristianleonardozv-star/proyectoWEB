package com.universidad.elecciones.dto;

import lombok.Data;

@Data
public class ResultadoVotoDTO {

    private Long candidatoId;
    private String candidatoNombre;
    private String candidatoDocumento;
    private Long votos;
    private Double porcentaje;
}
