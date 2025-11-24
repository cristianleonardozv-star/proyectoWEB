package com.universidad.elecciones.repository;

import com.universidad.elecciones.entity.Censo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CensoRepository extends JpaRepository<Censo, Long> {

    List<Censo> findByEleccionId(Long eleccionId);

    boolean existsByEleccionIdAndVotanteId(Long eleccionId, Long votanteId);
}
