package edu.arquetipo.jpa.entidades;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tareas")
public class Tarea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "fechaCreacion", nullable = false)
    private LocalDate fechaCreacion = LocalDate.now();

    @ManyToMany
    @JoinTable(name = "tarea_empleados", joinColumns = @JoinColumn(name = "tarea_id"), inverseJoinColumns = @JoinColumn(name = "usuario_id"))
    @JsonIgnoreProperties({ "tareasAsignadas", "tareasGestionadas", "subtareasAsignadas", "comentarios" })
    private Set<Usuario> empleadosAsignados = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "gestor_id")
    @JsonIgnoreProperties({ "tareasAsignadas", "tareasGestionadas", "subtareasAsignadas", "comentarios" })
    private Usuario gestorEncargado;

    private String estadoTarea;

    @OneToMany(mappedBy = "tareaAsociada", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<Subtarea> subtareas = new HashSet<>();

    @OneToMany(mappedBy = "tarea", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<Comentario> comentarios = new HashSet<>();

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Tarea{");
        sb.append("id=").append(id);
        sb.append(", nombre=").append(nombre);
        sb.append(", fechaCreacion=").append(fechaCreacion);
        sb.append(", empleadosAsignados=").append(empleadosAsignados);
        sb.append(", gestorEncargado=").append(gestorEncargado);
        sb.append(", estadoTarea=").append(estadoTarea);
        sb.append('}');
        return sb.toString();
    }

    // Constructor sin argumentos
    public Tarea() {
    }

    // Constructor para testing y registro de usuarios
    public Tarea(String nombre, LocalDate fechaCreacion, Set<Usuario> empleadosAsignados, Usuario gestorEncargado,
            String estadoTarea) {
        this.nombre = nombre;
        this.fechaCreacion = fechaCreacion;
        this.empleadosAsignados = empleadosAsignados;
        this.gestorEncargado = gestorEncargado;
        this.estadoTarea = estadoTarea;
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

    public Usuario getGestorEncargado() {
        return gestorEncargado;
    }

    public void setGestorEncargado(Usuario gestorEncargado) {
        this.gestorEncargado = gestorEncargado;
    }

    public String getEstadoTarea() {
        return estadoTarea;
    }

    public void setEstadoTarea(String estadoTarea) {
        this.estadoTarea = estadoTarea;
    }

    public Set<Subtarea> getSubtareas() {
        return subtareas;
    }

    public void setSubtareas(Set<Subtarea> subtareas) {
        this.subtareas = subtareas;
    }

    public Set<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(Set<Comentario> comentarios) {
        this.comentarios = comentarios;
    }
}