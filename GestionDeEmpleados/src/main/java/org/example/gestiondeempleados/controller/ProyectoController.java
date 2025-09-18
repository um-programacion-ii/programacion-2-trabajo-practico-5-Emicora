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

    /**
     * GET /api/proyectos
     * @return lista completa de proyectos (200 OK)
     */
    @GetMapping
    public List<Proyecto> listar() {
        return proyectoService.obtenerTodos();
    }

    /**
     * GET /api/proyectos/{id}
     * @param id id del proyecto
     * @return Proyecto encontrado (200 OK) o 404 si no existe
     */
    @GetMapping("/{id}")
    public Proyecto porId(@PathVariable Long id) {
        return proyectoService.buscarPorId(id);
    }

    /**
     * POST /api/proyectos
     * @param p datos del proyecto
     * @return Proyecto creado (201 Created)
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Proyecto crear(@Valid @RequestBody Proyecto p) {
        return proyectoService.guardar(p);
    }

    /**
     * PUT /api/proyectos/{id}
     * @param id id del proyecto a actualizar
     * @param p  datos a actualizar
     * @return Proyecto actualizado (200 OK) o 404 si no existe
     */
    @PutMapping("/{id}")
    public Proyecto actualizar(@PathVariable Long id, @Valid @RequestBody Proyecto p) {
        return proyectoService.actualizar(id, p);
    }

    /**
     * DELETE /api/proyectos/{id}
     * @param id id del proyecto a eliminar
     * @return 204 No Content si se elimina; 404 si no existe
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        proyectoService.eliminar(id);
    }

    /**
     * PUT /api/proyectos/{id}/empleados
     * @param id id del proyecto
     * @param empleadosIds Set de IDs de empleados a asignar
     * @return Proyecto con su set de empleados actualizado (200 OK)
     *
     * Ejemplo body: [1,2,3]
     */
    @PutMapping("/{id}/empleados")
    public Proyecto asignarEmpleados(@PathVariable Long id, @RequestBody Set<Long> empleadosIds) {
        return proyectoService.asignarEmpleados(id, empleadosIds);
    }

    /**
     * GET /api/proyectos/activos
     * @return lista de proyectos activos (200 OK)
     */
    @GetMapping("/activos")
    public List<Proyecto> activos() {
        return proyectoService.proyectosActivos();
    }
}