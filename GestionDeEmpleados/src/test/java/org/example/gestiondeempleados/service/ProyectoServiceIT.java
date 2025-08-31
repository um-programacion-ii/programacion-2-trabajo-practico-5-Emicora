package org.example.gestiondeempleados.service;

import org.example.gestiondeempleados.model.Proyecto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class ProyectoServiceIT {

    @Autowired
    private ProyectoService proyectoService;

    @Test
    void guardarYListarActivos() {
        Proyecto p = Proyecto.builder()
                .nombre("Proyecto X")
                .fechaFin(LocalDate.now().plusDays(10))
                .build();

        proyectoService.guardar(p);

        List<Proyecto> activos = proyectoService.proyectosActivos();

        assertThat(activos).extracting(Proyecto::getNombre).contains("Proyecto X");
    }
}