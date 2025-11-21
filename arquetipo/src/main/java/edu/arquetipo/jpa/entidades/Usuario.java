package edu.arquetipo.jpa.entidades;

import java.time.LocalDate;
import java.util.Set;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.*;

@Entity
@Table(name = "Usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idCliente")
    Cliente cliente;

    @ManyToMany
    @JoinTable(name = "Usuarios_has_Subtareas", joinColumns = @JoinColumn(name = "idSubtarea"), inverseJoinColumns = @JoinColumn(name = "idUsuario"))
    Set<Subtarea> subtareas;

    @ManyToMany
    @JoinTable(name = "Usuarios_has_Subtareas", joinColumns = @JoinColumn(name = "idSubtarea"), inverseJoinColumns = @JoinColumn(name = "idUsuario"))
    Set<RegistroHorario> registros;

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
            Set<RegistroHorario> registros, String rol, Set<Subtarea> subtareas, int tlf) {
        this.cliente = cliente;
        this.contrasena = contrasena;
        this.correo = correo;
        this.fechaNacimiento = fechaNacimiento;
        this.id = id;
        this.nombre = nombre;
        this.registros = registros;
        this.rol = rol;
        this.subtareas = subtareas;
        this.tlf = tlf;
    }

    @Override
    public String toString() {
        return "Usuario [Id=" + id + "\n Nombre=" + nombre + "\n Fecha de nacimiento=" + fechaNacimiento + "\n Rol="
                + rol
                + "\n Email=" + correo + "\n Teléfono=" + tlf + "]";
    }

    // Constructor para testing y registro de usuarios

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

}
