package com.universidad.elecciones.repository;

import com.universidad.elecciones.entity.Resultado;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ResultadoRepository extends JpaRepository<Resultado, Long> {

    boolean existsByEleccionIdAndCensoId(Long eleccionId, Long censoId);

    long countByEleccionIdAndCandidatoId(Long eleccionId, Long candidatoId);

    List<Resultado> findByEleccionId(Long eleccionId);
}

