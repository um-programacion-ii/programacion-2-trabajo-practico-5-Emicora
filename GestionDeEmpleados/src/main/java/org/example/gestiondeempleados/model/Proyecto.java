package org.example.gestiondeempleados.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "proyectos")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Proyecto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 100)
    @Column(nullable = false, length = 100)
    private String nombre;

    @Size(max = 1000)
    @Column(length = 1000)
    private String descripcion;

    @Column(name = "fecha_inicio")
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin")
    private LocalDate fechaFin;

    @ManyToMany(mappedBy = "proyectos")
    @Builder.Default
    private Set<Empleado> empleados = new HashSet<>();

    @Transient
    public boolean isActivo() {
        return fechaFin == null || fechaFin.isAfter(LocalDate.now());
    }
}