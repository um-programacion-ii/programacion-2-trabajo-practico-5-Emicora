package org.example.gestiondeempleados.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.gestiondeempleados.model.Proyecto;
import org.example.gestiondeempleados.service.ProyectoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ProyectoController.class)
class ProyectoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProyectoService proyectoService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void listarProyectosActivos_ok() throws Exception {
        Proyecto p = Proyecto.builder()
                .id(1L)
                .nombre("Proyecto Demo")
                .fechaFin(LocalDate.now().plusDays(5))
                .build();

        when(proyectoService.proyectosActivos()).thenReturn(List.of(p));

        mockMvc.perform(get("/api/proyectos/activos").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Proyecto Demo"));
    }
}