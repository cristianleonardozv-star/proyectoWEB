package com.universidad.elecciones.dto;

import lombok.Data;

@Data
public class CandidatoDTO {
    private Long id;
    private String documento;
    private String nombre;
    private String foto;
    private String planGobierno;
}
