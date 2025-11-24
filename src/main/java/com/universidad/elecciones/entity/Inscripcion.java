package com.universidad.elecciones.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "inscripcion")
@Getter @Setter
public class Inscripcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fechaInscripcion = LocalDate.now();

    @Enumerated(EnumType.STRING)
    private EstadoInscripcion estado = EstadoInscripcion.PENDIENTE;

    private Integer numeroTarjeton;

    @ManyToOne(optional = false)
    @JoinColumn(name = "eleccion_id")
    private Eleccion eleccion;

    @ManyToOne(optional = false)
    @JoinColumn(name = "candidato_id")
    private Candidato candidato;
}

