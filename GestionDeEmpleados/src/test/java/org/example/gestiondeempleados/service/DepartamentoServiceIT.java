package org.example.gestiondeempleados.service;

import org.example.gestiondeempleados.model.Departamento;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class DepartamentoServiceIT {

    @Autowired
    private DepartamentoService departamentoService;

    @Test
    void guardarYBuscarDepartamento() {
        Departamento d = Departamento.builder()
                .nombre("IT")
                .descripcion("Tecnología")
                .build();

        Departamento guardado = departamentoService.guardar(d);
        Departamento encontrado = departamentoService.buscarPorId(guardado.getId());

        assertThat(encontrado.getNombre()).isEqualTo("IT");
    }
}