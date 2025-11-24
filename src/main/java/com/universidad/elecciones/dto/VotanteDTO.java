package com.universidad.elecciones.dto;

import lombok.Data;

@Data
public class VotanteDTO {

    private Long id;
    private String documento;
    private String nombre;
    private String email;
    private String telefono;
}
