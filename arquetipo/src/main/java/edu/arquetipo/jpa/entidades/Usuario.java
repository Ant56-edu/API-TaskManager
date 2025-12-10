package edu.arquetipo.jpa.entidades;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
@Table(name = "Usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idCliente")
    @JsonIgnoreProperties({ "usuarios" })
    private Cliente cliente;

    @ManyToMany(mappedBy = "empleadosAsignados")
    @JsonIgnoreProperties({ "empleadosAsignados", "gestorEncargado", "subtareas", "comentarios" })
    private Set<Tarea> tareasAsignadas = new HashSet<>();

    @OneToMany(mappedBy = "gestorEncargado")
    @JsonIgnoreProperties({ "empleadosAsignados", "gestorEncargado", "subtareas", "comentarios" })
    private Set<Tarea> tareasGestionadas = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "Usuarios_has_Subtareas", joinColumns = @JoinColumn(name = "idUsuario"), inverseJoinColumns = @JoinColumn(name = "idSubtarea"))
    @JsonIgnoreProperties({ "usuariosAsignados", "tareaAsociada" })
    private Set<Subtarea> subtareasAsignadas = new HashSet<>();

    @OneToMany(mappedBy = "usuarios")
    @JsonIgnoreProperties({ "usuarios" })
    private Set<RegistroHorario> registros = new HashSet<>();

    @OneToMany(mappedBy = "usuario")
    @JsonIgnoreProperties({ "usuario", "tarea" })
    private Set<Comentario> comentarios = new HashSet<>();

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "fechaNacimiento")
    private String fechaNacimiento;

    @Column(name = "rol")
    private String rol;

    @Column(name = "correo")
    private String correo;

    @Column(name = "tlf")
    private int tlf;

    @Column(name = "contrasena")
    private String contrasena;

    public Usuario() {
    }

    public Usuario(Cliente cliente, String contrasena, String correo, String fechaNacimiento, Long id, String nombre,
            Set<RegistroHorario> registros, String rol, Set<Subtarea> subtareasAsignadas, int tlf) {
        this.cliente = cliente;
        this.contrasena = contrasena;
        this.correo = correo;
        this.fechaNacimiento = fechaNacimiento;
        this.id = id;
        this.nombre = nombre;
        this.registros = registros;
        this.rol = rol;
        this.subtareasAsignadas = subtareasAsignadas;
        this.tlf = tlf;
    }

    @Override
    public String toString() {
        return "Usuario [Id=" + id + "\n Nombre=" + nombre + "\n Fecha de nacimiento=" + fechaNacimiento + "\n Rol="
                + rol + "\n Email=" + correo + "\n Teléfono=" + tlf + "]";
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getTlf() {
        return tlf;
    }

    public void setTlf(int tlf) {
        this.tlf = tlf;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Set<Tarea> getTareasAsignadas() {
        return tareasAsignadas;
    }

    public void setTareasAsignadas(Set<Tarea> tareasAsignadas) {
        this.tareasAsignadas = tareasAsignadas;
    }

    public Set<Tarea> getTareasGestionadas() {
        return tareasGestionadas;
    }

    public void setTareasGestionadas(Set<Tarea> tareasGestionadas) {
        this.tareasGestionadas = tareasGestionadas;
    }

    public Set<Subtarea> getSubtareasAsignadas() {
        return subtareasAsignadas;
    }

    public void setSubtareasAsignadas(Set<Subtarea> subtareasAsignadas) {
        this.subtareasAsignadas = subtareasAsignadas;
    }

    public Set<RegistroHorario> getRegistros() {
        return registros;
    }

    public void setRegistros(Set<RegistroHorario> registros) {
        this.registros = registros;
    }

    public Set<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(Set<Comentario> comentarios) {
        this.comentarios = comentarios;
    }
}