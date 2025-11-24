package com.universidad.elecciones.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "candidato")
@Getter @Setter
public class Candidato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 20)
    private String documento;

    @Column(nullable = false, length = 150)
    private String nombre;

    @Column(length = 200)
    private String foto;  // opcional (ruta o nombre archivo)

    @Column(length = 300)
    private String planGobierno; // opcional
}
