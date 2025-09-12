package org.example.gestiondeempleados.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.gestiondeempleados.model.Proyecto;
import org.example.gestiondeempleados.repository.ProyectoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ProyectoControllerTest {

    @Autowired MockMvc mockMvc;
    @Autowired ObjectMapper mapper;
    @Autowired ProyectoRepository repo;

    @BeforeEach
    void setup() { repo.deleteAll(); }

    @Test
    void crear_y_listar_activos() throws Exception {
        Proyecto p = Proyecto.builder()
                .nombre("Proyecto X").fechaFin(LocalDate.now().plusDays(3)).build();

        mockMvc.perform(post("/api/proyectos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(p)))
                .andExpect(status().isCreated());

        mockMvc.perform(get("/api/proyectos/activos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Proyecto X"));
    }
}