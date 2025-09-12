package org.example.gestiondeempleados.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.gestiondeempleados.model.Departamento;
import org.example.gestiondeempleados.repository.DepartamentoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class DepartamentoControllerTest {

    @Autowired MockMvc mockMvc;
    @Autowired ObjectMapper mapper;
    @Autowired DepartamentoRepository repo;

    @BeforeEach
    void setup() { repo.deleteAll(); }

    @Test
    void crear_listar_y_obtener() throws Exception {
        Departamento d = Departamento.builder().nombre("RRHH").descripcion("Humanos").build();

        mockMvc.perform(post("/api/departamentos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(d)))
                .andExpect(status().isCreated());

        mockMvc.perform(get("/api/departamentos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("RRHH"));

        Long id = repo.findAll().get(0).getId();
        mockMvc.perform(get("/api/departamentos/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("RRHH"));
    }
}