package edu.arquetipo.jpa.entidades;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "Tareas")
public class Tarea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    // ** SOLUCIÓN FINAL A LA FECHA: Inicialización directa del campo. **
    // Esto asegura que el campo NUNCA será null al momento de la instanciación
    // por el constructor sin argumentos de Hibernate.
    @Column(name = "fecha_creacion", nullable = false)
    private LocalDate fechaCreacion = LocalDate.now();

    @ManyToMany
    @JoinTable(name = "tarea_empleados", joinColumns = @JoinColumn(name = "tarea_id"), inverseJoinColumns = @JoinColumn(name = "usuario_id"))
    private Set<Usuario> empleadosAsignados = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "gestor_id") // foreign key column in Tarea table
    private Usuario gestorEncargado;

    private String estadoTarea;

    @OneToMany(mappedBy = "tareaAsociada", cascade = CascadeType.ALL)
    private Set<Subtarea> subtareas;

    @OneToMany(mappedBy = "tarea", cascade = CascadeType.ALL)
    private Set<Comentario> comentarios;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Tarea{");
        sb.append("id=").append(id);
        sb.append(", nombre=").append(nombre);
        sb.append(", fechaCreacion=").append(fechaCreacion);
        sb.append(", empleadosAsignados=").append(empleadosAsignados);
        sb.append(", gestorEncargado=").append(gestorEncargado);
        sb.append(", estadoTarea").append(estadoTarea);
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
        // Si se pasa una fecha, se usa, si no, se usará el valor inicializado en la
        // declaración del campo.
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
        // Al asignar una fecha, esta anula el valor inicializado por defecto.
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

}