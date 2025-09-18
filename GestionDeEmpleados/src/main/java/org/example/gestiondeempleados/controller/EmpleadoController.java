package org.example.gestiondeempleados.controller;

import jakarta.validation.Valid;
import org.example.gestiondeempleados.model.Empleado;
import org.example.gestiondeempleados.service.EmpleadoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/empleados")
public class EmpleadoController {

    private final EmpleadoService empleadoService;

    public EmpleadoController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    /**
     * GET /api/empleados
     * @return lista completa de empleados (200 OK)
     */
    @GetMapping
    public List<Empleado> obtenerTodos() {
        return empleadoService.obtenerTodos();
    }

    /**
     * GET /api/empleados/{id}
     * @param id id del empleado
     * @return Empleado encontrado (200 OK) o 404 si no existe
     */
    @GetMapping("/{id}")
    public Empleado obtenerPorId(@PathVariable Long id) {
        return empleadoService.buscarPorId(id);
    }

    /**
     * POST /api/empleados
     * @param empleado cuerpo JSON con datos de empleado
     * @return Empleado creado (201 Created)
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Empleado crear(@Valid @RequestBody Empleado empleado) {
        return empleadoService.guardar(empleado);
    }

    /**
     * PUT /api/empleados/{id}
     * @param id id del empleado a actualizar
     * @param empleado datos a actualizar
     * @return Empleado actualizado (200 OK) o 404 si no existe
     */
    @PutMapping("/{id}")
    public Empleado actualizar(@PathVariable Long id, @Valid @RequestBody Empleado empleado) {
        return empleadoService.actualizar(id, empleado);
    }

    /**
     * DELETE /api/empleados/{id}
     * @param id id del empleado a eliminar
     * @return 204 No Content si se elimina; 404 si no existe
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        empleadoService.eliminar(id);
    }

    /**
     * GET /api/empleados/departamento/{nombre}
     * @param nombre nombre del departamento (e.g. "IT")
     * @return lista de empleados del depto (200 OK)
     */
    @GetMapping("/departamento/{nombre}")
    public List<Empleado> obtenerPorDepartamento(@PathVariable String nombre) {
        return empleadoService.buscarPorDepartamento(nombre);
    }

    /**
     * GET /api/empleados/salario?min=&max=
     * @param min salario mínimo (BigDecimal)
     * @param max salario máximo (BigDecimal)
     * @return lista de empleados dentro del rango (200 OK)
     *
     * Ejemplo:
     *  GET /api/empleados/salario?min=30000&max=60000
     */
    @GetMapping("/salario")
    public List<Empleado> obtenerPorRangoSalario(@RequestParam BigDecimal min,
                                                 @RequestParam BigDecimal max) {
        return empleadoService.buscarPorRangoSalario(min, max);
    }
}