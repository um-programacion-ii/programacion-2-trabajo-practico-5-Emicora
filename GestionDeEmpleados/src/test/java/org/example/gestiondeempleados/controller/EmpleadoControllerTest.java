package org.example.gestiondeempleados.controller;

import org.example.gestiondeempleados.model.Empleado;
import org.example.gestiondeempleados.service.EmpleadoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = EmpleadoController.class)
class EmpleadoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    // 👇 antes: @MockBean
    @MockitoBean
    private EmpleadoService empleadoService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void listarEmpleados_ok() throws Exception {
        Empleado e = Empleado.builder()
                .id(1L).nombre("Test").apellido("Uno")
                .email("test@empresa.com")
                .fechaContratacion(LocalDate.now())
                .salario(new BigDecimal("1000.00"))
                .build();

        when(empleadoService.obtenerTodos()).thenReturn(List.of(e));

        mockMvc.perform(get("/api/empleados").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].email").value("test@empresa.com"));
    }
}