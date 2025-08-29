package org.example.service.impl;

import jakarta.transaction.Transactional;
import org.example.exception.EmailDuplicadoException;
import org.example.exception.RecursoNoEncontradoException;
import org.example.model.Empleado;
import org.example.repository.DepartamentoRepository;
import org.example.repository.EmpleadoRepository;
import org.example.service.EmpleadoService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class EmpleadoServiceImpl implements EmpleadoService {

    private final EmpleadoRepository empleadoRepository;
    private final DepartamentoRepository departamentoRepository;

    public EmpleadoServiceImpl(EmpleadoRepository empleadoRepository,
                               DepartamentoRepository departamentoRepository) {
        this.empleadoRepository = empleadoRepository;
        this.departamentoRepository = departamentoRepository;
    }

    @Override
    public Empleado guardar(Empleado empleado) {
        empleadoRepository.findByEmail(empleado.getEmail()).ifPresent(e -> {
            throw new EmailDuplicadoException("El email ya está registrado: " + empleado.getEmail());
        });
        return empleadoRepository.save(empleado);
    }

    @Override
    public Empleado buscarPorId(Long id) {
        return empleadoRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Empleado no encontrado ID=" + id));
    }
    @Override
    public List<Empleado> buscarPorDepartamento(String nombreDepartamento) {
        return empleadoRepository.findByNombreDepartamento(nombreDepartamento);
    }

    @Override
    public List<Empleado> buscarPorRangoSalario(BigDecimal salarioMin, BigDecimal salarioMax) {
        return empleadoRepository.findBySalarioBetween(salarioMin, salarioMax);
    }

    @Override
    public BigDecimal obtenerSalarioPromedioPorDepartamento(Long departamentoId) {
        return empleadoRepository.findAverageSalarioByDepartamento(departamentoId)
                .orElse(BigDecimal.ZERO);
    }

    @Override
    public List<Empleado> obtenerTodos() {
        return empleadoRepository.findAll();
    }

    @Override
    public Empleado actualizar(Long id, Empleado empleado) {
        Empleado existente = buscarPorId(id);
        // si cambió el email, verificar duplicado
        if (!existente.getEmail().equalsIgnoreCase(empleado.getEmail())) {
            empleadoRepository.findByEmail(empleado.getEmail()).ifPresent(e -> {
                throw new EmailDuplicadoException("El email ya está registrado: " + empleado.getEmail());
            });
        }
        empleado.setId(id);
        return empleadoRepository.save(empleado);
    }

    @Override
    public void eliminar(Long id) {
        Empleado existente = buscarPorId(id);
        empleadoRepository.delete(existente);
    }
}