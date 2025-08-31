package org.example.gestiondeempleados.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.gestiondeempleados.model.Departamento;
import org.example.gestiondeempleados.service.DepartamentoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = DepartamentoController.class)
class DepartamentoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private DepartamentoService departamentoService;

    @Autowired private ObjectMapper objectMapper;

    @Test
    void listarDepartamentos_ok() throws Exception {
        Departamento d = Departamento.builder()
                .id(1L).nombre("Recursos Humanos").descripcion("RRHH").build();

        when(departamentoService.obtenerTodos()).thenReturn(List.of(d));

        mockMvc.perform(get("/api/departamentos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Recursos Humanos"));
    }
}