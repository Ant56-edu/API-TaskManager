package edu.arquetipo.jpa.entidades;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "Subtareas")
public class Subtarea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    @JoinTable(name = "subtarea_empleados", joinColumns = @JoinColumn(name = "subtarea_id"), inverseJoinColumns = @JoinColumn(name = "usuario_id"))
    private Set<Usuario> empleadosAsignados = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "tarea_id")
    private Tarea tareaAsociada; // must match 'mappedBy' in Tarea

    @Column(name = "nombre", nullable = false)
    private String nombre;
    @Column(name = "fechaCreacion", nullable = false)
    private LocalDate fechaCreacion;

    // Constructor sin argumentos
    public Subtarea() {
    }

    // Constructor para testing y registro de usuarios
    public Subtarea(Set<Usuario> empleadosAsignados, LocalDate fechaCreacion, String nombre,
            Tarea tareaAsociada) {
        this.empleadosAsignados = empleadosAsignados;
        this.fechaCreacion = fechaCreacion;
        this.nombre = nombre;
        this.tareaAsociada = tareaAsociada;
    }

    @Override
    public String toString() {
        return "Subtarea [id=" + id + ", nombre=" + nombre + ", fechaCreacion=" + fechaCreacion
                + ", empleadosAsignados=" + Arrays.toString(empleadosAsignados.toArray()) + ", tareaAsociada="
                + tareaAsociada.toString() + "]";
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Set<Usuario> getEmpleadosAsignados() {
        return empleadosAsignados;
    }

    public void setEmpleadosAsignados(Set<Usuario> empleadosAsignados) {
        this.empleadosAsignados = empleadosAsignados;
    }

    public Tarea getTareaAsociada() {
        return tareaAsociada;
    }

    public void setTareaAsociada(Tarea tareaAsociada) {
        this.tareaAsociada = tareaAsociada;
    }

}
