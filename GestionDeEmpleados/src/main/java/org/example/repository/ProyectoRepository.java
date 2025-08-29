package org.example.repository;

import org.example.model.Proyecto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface ProyectoRepository extends JpaRepository<Proyecto, Long> {

    @Query("SELECT p FROM Proyecto p WHERE p.fechaFin IS NULL OR p.fechaFin > :hoy")
    List<Proyecto> findProyectosActivos(LocalDate hoy);
}