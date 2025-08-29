package org.example.service;

import org.example.model.Proyecto;

import java.util.List;
import java.util.Set;

public interface ProyectoService {
    Proyecto guardar(Proyecto p);
    Proyecto buscarPorId(Long id);
    List<Proyecto> obtenerTodos();
    Proyecto actualizar(Long id, Proyecto p);
    void eliminar(Long id);

    Proyecto asignarEmpleados(Long proyectoId, Set<Long> empleadosIds);

    List<Proyecto> proyectosActivos();
}
