package com.universidad.elecciones.dto;

import lombok.Data;

@Data
public class ProgramaDTO {
    private Long id;
    private String nombre;
    private Long facultadId;
    private String facultadNombre;
}
