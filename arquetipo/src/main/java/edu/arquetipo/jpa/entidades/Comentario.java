package edu.arquetipo.jpa.entidades;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "Comentarios")
public class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario; // This is the field name

    @ManyToOne
    @JoinColumn(name = "tarea_id")
    private Tarea tarea; // This is the field name

    @Column(name = "id_tarea")
    private Long idTarea;
    @Column(name = "id_usuario")
    private Long idUsuario;
    @Column(name = "contenido")
    private String contenido;
    @Column(name = "fecha_hora_subida")
    private LocalDateTime fechaHoraSubida;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(Long idTarea) {
        this.idTarea = idTarea;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public LocalDateTime getFechaHoraSubida() {
        return fechaHoraSubida;
    }

    public void setFechaHoraSubida(LocalDateTime fechaHoraSubida) {
        this.fechaHoraSubida = fechaHoraSubida;
    }

    public Comentario() {
    }

    public Comentario(String contenido, LocalDateTime fechaHoraSubida, Long idTarea, Long idUsuario) {
        this.contenido = contenido;
        this.fechaHoraSubida = fechaHoraSubida;
        this.idTarea = idTarea;
        this.idUsuario = idUsuario;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Comentario{");
        sb.append("id=").append(id);
        sb.append(", idTarea=").append(idTarea);
        sb.append(", idUsuario=").append(idUsuario);
        sb.append(", contenido=").append(contenido);
        sb.append(", fechaHoraSubida=").append(fechaHoraSubida);
        sb.append('}');
        return sb.toString();
    }
}
