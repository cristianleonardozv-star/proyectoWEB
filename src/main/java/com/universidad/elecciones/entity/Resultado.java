package com.universidad.elecciones.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "resultado")
@Getter @Setter
public class Resultado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // FK a eleccion
    @ManyToOne(optional = false)
    @JoinColumn(name = "eleccion_id")
    private Eleccion eleccion;

    // FK a candidato
    @ManyToOne(optional = false)
    @JoinColumn(name = "candidato_id")
    private Candidato candidato;

    // FK a censo
    @ManyToOne(optional = false)
    @JoinColumn(name = "censo_id")
    private Censo censo;

    @Column(nullable = false)
    private String documento;

    @Column(nullable = false)
    private String ip;

    @Column(nullable = false)
    private String uuid;

    private LocalDateTime fecha_creacion = LocalDateTime.now();

    @Column(nullable = false)
    private String estado; // EJ: REGISTRADO, ANULADO
}
