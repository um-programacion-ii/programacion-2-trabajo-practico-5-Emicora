package org.example.gestiondeempleados.service;

import org.example.gestiondeempleados.model.Departamento;
import org.example.gestiondeempleados.model.Empleado;
import org.example.gestiondeempleados.repository.DepartamentoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class EmpleadoServiceIT {

    @Autowired
    private EmpleadoService empleadoService;
    @Autowired
    private DepartamentoRepository departamentoRepository;

    @Test
    void guardarEmpleado_ok() {
        Departamento d = departamentoRepository.save(
                Departamento.builder().nombre("RRHH").build());

        Empleado e = Empleado.builder()
                .nombre("Ana")
                .apellido("García")
                .email("ana.garcia@empresa.com")
                .fechaContratacion(LocalDate.now())
                .salario(new BigDecimal("75000.00"))
                .departamento(d)
                .build();

        Empleado guardado = empleadoService.guardar(e);
        assertThat(guardado.getId()).isNotNull();
        assertThat(guardado.getEmail()).isEqualTo("ana.garcia@empresa.com");
    }
}