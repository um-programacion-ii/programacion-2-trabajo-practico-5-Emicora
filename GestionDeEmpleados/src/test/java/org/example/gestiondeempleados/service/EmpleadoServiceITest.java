package org.example.gestiondeempleados.service;

import org.example.gestiondeempleados.exception.EmailDuplicadoException;
import org.example.gestiondeempleados.exception.RecursoNoEncontradoException;
import org.example.gestiondeempleados.model.Empleado;
import org.example.gestiondeempleados.repository.DepartamentoRepository;
import org.example.gestiondeempleados.repository.EmpleadoRepository;
import org.example.gestiondeempleados.service.impl.EmpleadoServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmpleadoServiceITest {

    @Mock EmpleadoRepository empleadoRepository;
    @Mock DepartamentoRepository departamentoRepository;

    @InjectMocks EmpleadoServiceImpl service;

    @Test
    void guardar_ok() {
        Empleado e = Empleado.builder()
                .nombre("Ana").apellido("García").email("ana@empresa.com")
                .fechaContratacion(LocalDate.now()).salario(new BigDecimal("1000")).build();


        when(empleadoRepository.findByEmail("ana@empresa.com")).thenReturn(Optional.empty());
        when(empleadoRepository.save(any())).thenAnswer(i -> {
            Empleado saved = i.getArgument(0);
            saved.setId(10L);
            return saved;
        });

        Empleado out = service.guardar(e);

        assertThat(out.getId()).isEqualTo(10L);
        verify(empleadoRepository).save(any(Empleado.class));
    }

    @Test
    void guardar_emailDuplicado_lanzaExcepcion() {
        when(empleadoRepository.findByEmail("ana@empresa.com"))
                .thenReturn(Optional.of(new Empleado()));

        Empleado e = Empleado.builder().email("ana@empresa.com").build();

        assertThatThrownBy(() -> service.guardar(e))
                .isInstanceOf(EmailDuplicadoException.class);
        verify(empleadoRepository, never()).save(any());
    }

    @Test
    void buscarPorId_noExiste_lanzaNotFound() {
        when(empleadoRepository.findById(99L)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> service.buscarPorId(99L))
                .isInstanceOf(RecursoNoEncontradoException.class);
    }
}
