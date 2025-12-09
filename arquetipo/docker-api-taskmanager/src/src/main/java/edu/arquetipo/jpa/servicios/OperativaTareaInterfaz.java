package edu.arquetipo.jpa.servicios;

import java.util.List;

import edu.arquetipo.jpa.entidades.Tarea;

public interface OperativaTareaInterfaz {

    Tarea buscarTarea(long id);

    Tarea crearTarea(Tarea tarea);

    Tarea editarDetalles(long id, Tarea tareaActualizada);

    void borrarTarea(long id);

    List<Tarea> listarTareas();

}
