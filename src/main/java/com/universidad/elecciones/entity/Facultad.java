package com.universidad.elecciones.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "facultad")
@Getter
@Setter
public class Facultad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, unique = true, length = 150)
    private String nombre;

    // Si quieres la relación inversa:
    // @OneToMany(mappedBy = "facultad")
    // private List<Programa> programas = new ArrayList<>();
}
