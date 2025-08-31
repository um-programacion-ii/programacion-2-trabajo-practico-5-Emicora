package org.example.gestiondeempleados.service;



import org.example.gestiondeempleados.model.Departamento;

import java.math.BigDecimal;
import java.util.List;

public interface DepartamentoService {
    Departamento guardar(Departamento dpto);
    Departamento buscarPorId(Long id);
    List<Departamento> obtenerTodos();
    Departamento actualizar(Long id, Departamento dpto);
    void eliminar(Long id);
    BigDecimal salarioPromedio(Long departamentoId);
}