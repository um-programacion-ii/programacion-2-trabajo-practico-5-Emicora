package org.example.gestiondeempleados.service;

import org.example.gestiondeempleados.exception.RecursoNoEncontradoException;
import org.example.gestiondeempleados.model.Empleado;
import org.example.gestiondeempleados.model.Proyecto;
import org.example.gestiondeempleados.repository.EmpleadoRepository;
import org.example.gestiondeempleados.repository.ProyectoRepository;
import org.example.gestiondeempleados.service.impl.ProyectoServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProyectoServiceITest {

    @Mock ProyectoRepository proyectoRepository;
    @Mock EmpleadoRepository empleadoRepository;
    @InjectMocks ProyectoServiceImpl service;

    @Test
    void proyectosActivos_delegaEnRepo() {
        when(proyectoRepository.findProyectosActivos(any()))
                .thenReturn(List.of(Proyecto.builder().nombre("A").build()));
        assertThat(service.proyectosActivos()).hasSize(1);
    }

    @Test
    void asignarEmpleados_ok() {
        Proyecto p = Proyecto.builder().id(1L).nombre("Demo").build();
        when(proyectoRepository.findById(1L)).thenReturn(Optional.of(p));

        Empleado e1 = Empleado.builder().id(10L).build();
        when(empleadoRepository.findAllById(Set.of(10L))).thenReturn(List.of(e1));
        when(proyectoRepository.save(any())).thenAnswer(a -> a.getArgument(0));

        Proyecto out = service.asignarEmpleados(1L, Set.of(10L));
        assertThat(out.getEmpleados()).hasSize(1);
    }

    @Test
    void buscarPorId_notFound() {
        when(proyectoRepository.findById(99L)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> service.buscarPorId(99L))
                .isInstanceOf(RecursoNoEncontradoException.class);
    }
}