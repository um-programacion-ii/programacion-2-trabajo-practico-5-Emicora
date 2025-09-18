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

    /**
     * GET /api/departamentos
     * @return lista completa de departamentos (200 OK)
     */
    @GetMapping
    public List<Departamento> listar() {
        return departamentoService.obtenerTodos();
    }

    /**
     * GET /api/departamentos/{id}
     * @param id identificador del departamento
     * @return Departamento encontrado (200 OK) o error si no existe (404)
     */
    @GetMapping("/{id}")
    public Departamento porId(@PathVariable Long id) {
        return departamentoService.buscarPorId(id);
    }

    /**
     * POST /api/departamentos
     * @param d cuerpo JSON con nombre/descripcion
     * @return Departamento creado (201 Created)
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Departamento crear(@Valid @RequestBody Departamento d) {
        return departamentoService.guardar(d);
    }
  /**
     * PUT /api/departamentos/{id}
     * @param id id del departamento a actualizar
     * @param d  datos a actualizar
     * @return Departamento actualizado (200 OK) o 404 si no existe
     */
    @PutMapping("/{id}")
    public Departamento actualizar(@PathVariable Long id, @Valid @RequestBody Departamento d) {
        return departamentoService.actualizar(id, d);
    }

    /**
     * DELETE /api/departamentos/{id}
     * @param id id del departamento a eliminar
     * @return 204 No Content si se elimina; 404 si no existe
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        departamentoService.eliminar(id);
    }

    /**
     * GET /api/departamentos/{id}/salario-promedio
     * @param id departamento
     * @return BigDecimal con salario promedio (200 OK)
     *         Si no hay empleados, devolverá 0 según la impl del servicio.
     */
    @GetMapping("/{id}/salario-promedio")
    public BigDecimal salarioPromedio(@PathVariable Long id) {
        return departamentoService.salarioPromedio(id);
    }
}