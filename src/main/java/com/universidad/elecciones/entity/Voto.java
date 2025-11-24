package com.universidad.elecciones.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Table(name = "voto")
@Getter @Setter
public class Voto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime fechaVoto = LocalDateTime.now();

    @ManyToOne(optional = false)
    @JoinColumn(name = "eleccion_id")
    private Eleccion eleccion;

    @ManyToOne(optional = false)
    @JoinColumn(name = "votante_id")
    private Votante votante;

    @ManyToOne(optional = false)
    @JoinColumn(name = "candidato_id")
    private Candidato candidato;
}
