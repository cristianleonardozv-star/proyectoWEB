package com.universidad.elecciones.service.impl;

import com.universidad.elecciones.dto.EleccionRequestDTO;
import com.universidad.elecciones.dto.EleccionResponseDTO;
import com.universidad.elecciones.entity.*;
import com.universidad.elecciones.repository.*;
import com.universidad.elecciones.service.EleccionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EleccionServiceImpl implements EleccionService {

    private final EleccionRepository repo;
    private final FacultadRepository facultadRepo;
    private final ProgramaRepository programaRepo;
    private final SedeRepository sedeRepo;
    private final ProcesoRepository procesoRepo;
    private final TipoRepository tipoRepo;
    private final TipoEleccionRepository tipoEleccionRepo;

    public EleccionServiceImpl(EleccionRepository repo,
                               FacultadRepository facultadRepo,
                               ProgramaRepository programaRepo,
                               SedeRepository sedeRepo,
                               ProcesoRepository procesoRepo,
                               TipoRepository tipoRepo,
                               TipoEleccionRepository tipoEleccionRepo) {

        this.repo = repo;
        this.facultadRepo = facultadRepo;
        this.programaRepo = programaRepo;
        this.sedeRepo = sedeRepo;
        this.procesoRepo = procesoRepo;
        this.tipoRepo = tipoRepo;
        this.tipoEleccionRepo = tipoEleccionRepo;
    }

    private EleccionResponseDTO map(Eleccion e) {
        EleccionResponseDTO dto = new EleccionResponseDTO();

        dto.setId(e.getId());
        dto.setNombre(e.getNombre());
        dto.setDescripcion(e.getDescripcion());
        dto.setAnio(e.getAnio());
        dto.setSemestre(e.getSemestre());

        dto.setFacultad(e.getFacultad().getNombre());
        dto.setPrograma(e.getPrograma().getNombre());
        dto.setSede(e.getSede().getNombre());
        dto.setProceso(e.getProceso().getNombre());
        dto.setTipo(e.getTipo().getNombre());
        dto.setTipoEleccion(e.getTipoEleccion().getNombre());

        dto.setFechaInicio(e.getFechaInicio().toString());
        dto.setFechaFin(e.getFechaFin().toString());

        dto.setEstado(e.getEstado().name());

        return dto;
    }

    @Override
    public EleccionResponseDTO crear(EleccionRequestDTO d) {

        Eleccion e = new Eleccion();

        e.setNombre(d.getNombre());
        e.setDescripcion(d.getDescripcion());
        e.setAnio(d.getAnio());
        e.setSemestre(d.getSemestre());
        e.setFechaInicio(d.getFechaInicio());
        e.setFechaFin(d.getFechaFin());

        e.setFacultad(facultadRepo.findById(d.getFacultadId()).orElseThrow());
        e.setPrograma(programaRepo.findById(d.getProgramaId()).orElseThrow());
        e.setSede(sedeRepo.findById(d.getSedeId()).orElseThrow());
        e.setProceso(procesoRepo.findById(d.getProcesoId()).orElseThrow());
        e.setTipo(tipoRepo.findById(d.getTipoId()).orElseThrow());
        e.setTipoEleccion(tipoEleccionRepo.findById(d.getTipoEleccionId()).orElseThrow());

        e.setEstado(EstadoEleccion.CREADA);

        return map(repo.save(e));
    }

    @Override
    public List<EleccionResponseDTO> listar() {
        return repo.findAll().stream().map(this::map).collect(Collectors.toList());
    }

    @Override
    public EleccionResponseDTO buscarPorId(Long id) {
        return map(repo.findById(id).orElseThrow(() -> new RuntimeException("Elección no encontrada")));
    }

    @Override
    public EleccionResponseDTO abrir(Long id) {
        Eleccion e = repo.findById(id).orElseThrow();
        e.setEstado(EstadoEleccion.ABIERTA);
        return map(repo.save(e));
    }

    @Override
    public EleccionResponseDTO cerrar(Long id) {
        Eleccion e = repo.findById(id).orElseThrow();
        e.setEstado(EstadoEleccion.CERRADA);
        return map(repo.save(e));
    }
}
