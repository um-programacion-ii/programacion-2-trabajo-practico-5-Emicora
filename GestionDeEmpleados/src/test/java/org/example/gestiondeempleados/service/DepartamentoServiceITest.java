package org.example.gestiondeempleados.service;

import org.example.gestiondeempleados.exception.RecursoNoEncontradoException;
import org.example.gestiondeempleados.model.Departamento;
import org.example.gestiondeempleados.repository.DepartamentoRepository;
import org.example.gestiondeempleados.repository.EmpleadoRepository;
import org.example.gestiondeempleados.service.impl.DepartamentoServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DepartamentoServiceITest {

    @Mock DepartamentoRepository departamentoRepository;
    @Mock EmpleadoRepository empleadoRepository;
    @InjectMocks DepartamentoServiceImpl service;

    @Test
    void actualizar_ok() {
        Departamento existente = Departamento.builder().id(1L).nombre("IT").build();
        Departamento entrada = Departamento.builder().nombre("Tech").build();

        when(departamentoRepository.findById(1L)).thenReturn(Optional.of(existente));
        when(departamentoRepository.save(any())).thenAnswer(a -> a.getArgument(0));

        Departamento out = service.actualizar(1L, entrada);

        assertThat(out.getId()).isEqualTo(1L);
        assertThat(out.getNombre()).isEqualTo("Tech");
    }

    @Test
    void buscarPorId_notFound() {
        when(departamentoRepository.findById(7L)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> service.buscarPorId(7L))
                .isInstanceOf(RecursoNoEncontradoException.class);
    }

    @Test
    void salarioPromedio_delegaEnRepoEmpleados() {
        when(empleadoRepository.findAverageSalarioByDepartamento(5L))
                .thenReturn(Optional.of(new BigDecimal("123.45")));
        BigDecimal avg = service.salarioPromedio(5L);
        assertThat(avg).isEqualByComparingTo("123.45");
    }
}