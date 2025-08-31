package org.example.gestiondeempleados.controller;

import jakarta.validation.Valid;
import org.example.gestiondeempleados.model.Proyecto;
import org.example.gestiondeempleados.service.ProyectoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/proyectos")
public class ProyectoController {

    private final ProyectoService proyectoService;

    public ProyectoController(ProyectoService proyectoService) {
        this.proyectoService = proyectoService;
    }

    @GetMapping
    public List<Proyecto> listar() {
        return proyectoService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Proyecto porId(@PathVariable Long id) {
        return proyectoService.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Proyecto crear(@Valid @RequestBody Proyecto p) {
        return proyectoService.guardar(p);
    }

    @PutMapping("/{id}")
    public Proyecto actualizar(@PathVariable Long id, @Valid @RequestBody Proyecto p) {
        return proyectoService.actualizar(id, p);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        proyectoService.eliminar(id);
    }

    @PutMapping("/{id}/empleados")
    public Proyecto asignarEmpleados(@PathVariable Long id, @RequestBody Set<Long> empleadosIds) {
        return proyectoService.asignarEmpleados(id, empleadosIds);
    }

    @GetMapping("/activos")
    public List<Proyecto> activos() {
        return proyectoService.proyectosActivos();
    }
}