package org.example.gestiondeempleados.repository;


import org.example.gestiondeempleados.model.Departamento;
import org.example.gestiondeempleados.model.Empleado;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class EmpleadoRepositoryTest {

    @Autowired
    private EmpleadoRepository empleadoRepository;
    @Autowired
    private DepartamentoRepository departamentoRepository;

    @Test
    void guardarYBuscarPorDepartamento() {
        Departamento it = departamentoRepository.save(
                Departamento.builder().nombre("IT").descripcion("Tech").build());

        Empleado e = Empleado.builder()
                .nombre("Juan")
                .apellido("Pérez")
                .email("juan.perez@empresa.com")
                .fechaContratacion(LocalDate.now())
                .salario(new BigDecimal("50000.00"))
                .departamento(it)
                .build();

        empleadoRepository.save(e);

        List<Empleado> enIT = empleadoRepository.findByNombreDepartamento("IT");
        assertThat(enIT).extracting(Empleado::getEmail).contains("juan.perez@empresa.com");
    }
}