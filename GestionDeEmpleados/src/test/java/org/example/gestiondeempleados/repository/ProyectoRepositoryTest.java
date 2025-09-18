package org.example.gestiondeempleados.repository;

import org.example.gestiondeempleados.model.Proyecto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ProyectoRepositoryTest {

    @Autowired
    private ProyectoRepository proyectoRepository;

    @Test
    void buscarProyectosActivos() {
        Proyecto activo = Proyecto.builder()
                .nombre("Proyecto A")
                .fechaFin(LocalDate.now().plusDays(5))
                .build();
        Proyecto inactivo = Proyecto.builder()
                .nombre("Proyecto B")
                .fechaFin(LocalDate.now().minusDays(5))
                .build();

        proyectoRepository.saveAll(List.of(activo, inactivo));

        List<Proyecto> activos = proyectoRepository.findProyectosActivos(LocalDate.now());

        assertThat(activos).extracting(Proyecto::getNombre).contains("Proyecto A");
        assertThat(activos).extracting(Proyecto::getNombre).doesNotContain("Proyecto B");
    }
}