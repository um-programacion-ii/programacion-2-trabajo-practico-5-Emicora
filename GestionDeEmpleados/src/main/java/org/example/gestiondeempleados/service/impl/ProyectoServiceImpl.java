package org.example.gestiondeempleados.service.impl;

import jakarta.transaction.Transactional;
import org.example.gestiondeempleados.exception.RecursoNoEncontradoException;
import org.example.gestiondeempleados.model.Empleado;
import org.example.gestiondeempleados.model.Proyecto;
import org.example.gestiondeempleados.repository.EmpleadoRepository;
import org.example.gestiondeempleados.repository.ProyectoRepository;
import org.example.gestiondeempleados.service.ProyectoService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class ProyectoServiceImpl implements ProyectoService {

    private final ProyectoRepository proyectoRepository;
    private final EmpleadoRepository empleadoRepository;

    public ProyectoServiceImpl(ProyectoRepository proyectoRepository,
                               EmpleadoRepository empleadoRepository) {
        this.proyectoRepository = proyectoRepository;
        this.empleadoRepository = empleadoRepository;
    }

    @Override
    public Proyecto guardar(Proyecto p) {
        return proyectoRepository.save(p);
    }

    @Override
    public Proyecto buscarPorId(Long id) {
        return proyectoRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Proyecto no encontrado ID=" + id));
    }

    @Override
    public List<Proyecto> obtenerTodos() {
        return proyectoRepository.findAll();
    }

    @Override
    public Proyecto actualizar(Long id, Proyecto p) {
        Proyecto existente = buscarPorId(id);
        p.setId(existente.getId());
        return proyectoRepository.save(p);
    }

    @Override
    public void eliminar(Long id) {
        Proyecto p = buscarPorId(id);
        proyectoRepository.delete(p);
    }

    @Override
    public Proyecto asignarEmpleados(Long proyectoId, Set<Long> empleadosIds) {
        Proyecto proyecto = buscarPorId(proyectoId);
        Set<Empleado> nuevos = new HashSet<>(empleadoRepository.findAllById(empleadosIds));
        proyecto.getEmpleados().clear();
        proyecto.getEmpleados().addAll(nuevos);
        // sincronizar lado inverso
        for (   Empleado e : nuevos) {
            e.getProyectos().add(proyecto);
        }
        return proyectoRepository.save(proyecto);
    }

    @Override
    public List<Proyecto> proyectosActivos() {
        return proyectoRepository.findProyectosActivos(LocalDate.now());
    }
}