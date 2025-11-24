package com.universidad.elecciones.repository;

import com.universidad.elecciones.entity.Voto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VotoRepository extends JpaRepository<Voto, Long> {

    boolean existsByEleccionIdAndVotanteId(Long eleccionId, Long votanteId);

    long countByEleccionIdAndCandidatoId(Long eleccionId, Long candidatoId);

    long countByEleccionId(Long eleccionId);

    @Query("SELECT v.candidato.id, v.candidato.nombre, v.candidato.documento, COUNT(v) " +
           "FROM Voto v " +
           "WHERE v.eleccion.id = :eleccionId " +
           "GROUP BY v.candidato.id, v.candidato.nombre, v.candidato.documento " +
           "ORDER BY COUNT(v) DESC")
    List<Object[]> contarVotosPorCandidato(@Param("eleccionId") Long eleccionId);
}
