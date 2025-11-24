package com.universidad.elecciones.dto;

import lombok.Data;

@Data
public class EleccionResponseDTO {

    private Long id;

    private String nombre;
    private String descripcion;
    private Integer anio;
    private Integer semestre;

    private String facultad;
    private String programa;
    private String sede;
    private String proceso;
    private String tipo;
    private String tipoEleccion;

    private String estado;

    private String fechaInicio;
    private String fechaFin;
}
