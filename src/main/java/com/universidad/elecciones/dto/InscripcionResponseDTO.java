package com.universidad.elecciones.dto;

import lombok.Data;

@Data
public class InscripcionResponseDTO {
    private Long id;
    private String candidatoNombre;
    private String candidatoDocumento;
    private String estado;
    private Integer numeroTarjeton;
}
