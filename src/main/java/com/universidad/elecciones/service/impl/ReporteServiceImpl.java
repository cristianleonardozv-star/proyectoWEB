package com.universidad.elecciones.service.impl;

import com.universidad.elecciones.dto.ResultadoVotoDTO;
import com.universidad.elecciones.repository.VotoRepository;
import com.universidad.elecciones.service.ReporteService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReporteServiceImpl implements ReporteService {

    private final VotoRepository votoRepository;

    public ReporteServiceImpl(VotoRepository votoRepository) {
        this.votoRepository = votoRepository;
    }

    @Override
    public List<ResultadoVotoDTO> resultadosPorEleccion(Long eleccionId) {

        long totalVotos = votoRepository.countByEleccionId(eleccionId);
        List<Object[]> filas = votoRepository.contarVotosPorCandidato(eleccionId);

        List<ResultadoVotoDTO> resultados = new ArrayList<>();

        for (Object[] f : filas) {
            Long idCandidato = (Long) f[0];
            String nombre = (String) f[1];
            String documento = (String) f[2];
            Long votos = (Long) f[3];

            ResultadoVotoDTO dto = new ResultadoVotoDTO();
            dto.setCandidatoId(idCandidato);
            dto.setCandidatoNombre(nombre);
            dto.setCandidatoDocumento(documento);
            dto.setVotos(votos);

            if (totalVotos > 0) {
                double porcentaje = (votos * 100.0) / totalVotos;
                dto.setPorcentaje(Math.round(porcentaje * 100.0) / 100.0); // 2 decimales
            } else {
                dto.setPorcentaje(0.0);
            }

            resultados.add(dto);
        }

        return resultados;
    }
}

