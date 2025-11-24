package com.universidad.elecciones.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "censo")
@Getter @Setter
public class Censo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "eleccion_id")
    private Eleccion eleccion;

    @ManyToOne(optional = false)
    @JoinColumn(name = "votante_id")
    private Votante votante;
}
