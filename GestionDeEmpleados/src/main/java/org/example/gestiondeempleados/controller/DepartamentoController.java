package org.example.gestiondeempleados.controller;

import jakarta.validation.Valid;
import org.example.gestiondeempleados.model.Departamento;
import org.example.gestiondeempleados.service.DepartamentoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/departamentos")
public class DepartamentoController {

    private final DepartamentoService departamentoService;

    public DepartamentoController(DepartamentoService departamentoService) {
        this.departamentoService = departamentoService;
    }

    @GetMapping
    public List<Departamento> listar() {
        return departamentoService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Departamento porId(@PathVariable Long id) {
        return departamentoService.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Departamento crear(@Valid @RequestBody Departamento d) {
        return departamentoService.guardar(d);
    }

    @PutMapping("/{id}")
    public Departamento actualizar(@PathVariable Long id, @Valid @RequestBody Departamento d) {
        return departamentoService.actualizar(id, d);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        departamentoService.eliminar(id);
    }

    @GetMapping("/{id}/salario-promedio")
    public BigDecimal salarioPromedio(@PathVariable Long id) {
        return departamentoService.salarioPromedio(id);
    }
}