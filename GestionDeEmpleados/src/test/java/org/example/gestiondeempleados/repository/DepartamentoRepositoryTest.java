package org.example.gestiondeempleados.repository;

import org.example.gestiondeempleados.model.Departamento;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class DepartamentoRepositoryTest {

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @Test
    void guardarYBuscarPorNombre() {
        Departamento d = Departamento.builder()
                .nombre("Finanzas")
                .descripcion("Depto de Finanzas")
                .build();

        departamentoRepository.save(d);

        Optional<Departamento> encontrado = departamentoRepository.findByNombre("Finanzas");

        assertThat(encontrado).isPresent();
        assertThat(encontrado.get().getDescripcion()).isEqualTo("Depto de Finanzas");
    }
}