package org.example.gestiondeempleados.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "empleados",
        uniqueConstraints = @UniqueConstraint(name = "uk_empleado_email", columnNames = "email"))
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 100)
    @Column(nullable = false, length = 100)
    private String nombre;

    @NotBlank
    @Size(max = 100)
    @Column(nullable = false, length = 100)
    private String apellido;

    @Email
    @NotBlank
    @Column(nullable = false, length = 150)
    private String email;

    @NotNull
    @Column(name = "fecha_contratacion", nullable = false)
    private LocalDate fechaContratacion;

    @NotNull
    @DecimalMin(value = "0.00")
    @Digits(integer = 12, fraction = 2)
    @Column(nullable = false, precision = 14, scale = 2)
    private BigDecimal salario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "departamento_id",
            foreignKey = @ForeignKey(name = "fk_empleado_departamento"))
    private Departamento departamento;

    @ManyToMany
    @JoinTable(
            name = "empleado_proyecto",
            joinColumns = @JoinColumn(name = "empleado_id",
                    foreignKey = @ForeignKey(name = "fk_empleado_proyecto_emp")),
            inverseJoinColumns = @JoinColumn(name = "proyecto_id",
                    foreignKey = @ForeignKey(name = "fk_empleado_proyecto_proy"))
    )
    @Builder.Default
    private Set<Proyecto> proyectos = new HashSet<>();
}