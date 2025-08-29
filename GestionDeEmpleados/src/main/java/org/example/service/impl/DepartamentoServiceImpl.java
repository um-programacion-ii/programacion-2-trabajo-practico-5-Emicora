package org.example.service.impl;

import jakarta.transaction.Transactional;
import org.example.exception.RecursoNoEncontradoException;
import org.example.model.Departamento;
import org.example.repository.DepartamentoRepository;
import org.example.repository.EmpleadoRepository;
import org.example.service.DepartamentoService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class DepartamentoServiceImpl implements DepartamentoService {

    private final DepartamentoRepository departamentoRepository;
    private final EmpleadoRepository empleadoRepository;

    public DepartamentoServiceImpl(DepartamentoRepository departamentoRepository,
                                   EmpleadoRepository empleadoRepository) {
        this.departamentoRepository = departamentoRepository;
        this.empleadoRepository = empleadoRepository;
    }

    @Override
    public Departamento guardar(Departamento dpto) {
        return departamentoRepository.save(dpto);
    }

    @Override
    public Departamento buscarPorId(Long id) {
        return departamentoRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Departamento no encontrado ID=" + id));
    }

    @Override
    public List<Departamento> obtenerTodos() {
        return departamentoRepository.findAll();
    }

    @Override
    public Departamento actualizar(Long id, Departamento dpto) {
        Departamento existente = buscarPorId(id);
        dpto.setId(existente.getId());
        return departamentoRepository.save(dpto);
    }

    @Override
    public void eliminar(Long id) {
        Departamento existente = buscarPorId(id);
        departamentoRepository.delete(existente);
    }

    @Override
    public BigDecimal salarioPromedio(Long departamentoId) {
        return empleadoRepository.findAverageSalarioByDepartamento(departamentoId).orElse(BigDecimal.ZERO);
    }
}
