package com.universidad.elecciones.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EleccionRequestDTO {

    private String nombre;
    private String descripcion;

    private Integer anio;
    private Integer semestre;

    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    private Long facultadId;
    private Long programaId;
    private Long sedeId;
    private Long procesoId;
    private Long tipoId;
    private Long tipoEleccionId;
}
