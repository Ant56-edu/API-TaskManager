package edu.arquetipo.jpa.servicios;

import java.util.List;

import org.springframework.stereotype.Service;

import edu.arquetipo.jpa.dao.TareaDAO;
import edu.arquetipo.jpa.entidades.Tarea;

@Service
public class OperativaTareaImplementacion implements OperativaTareaInterfaz {

    private final TareaDAO dao;

    public OperativaTareaImplementacion(TareaDAO dao) {
        this.dao = dao;
    }

    @Override
    public Tarea buscarTarea(long id) {
        return dao.buscar(id);
    }

    @Override
    public Tarea crearTarea(Tarea tarea) {
        dao.insertar(tarea);
        System.out.println(tarea.toString());
        return dao.buscar(tarea.getId());
    }

    @Override
    public Tarea editarDetalles(long id, Tarea tareaActualizada) {
        dao.editar(id, tareaActualizada);
        return dao.buscar(id);
    }

    @Override
    public void borrarTarea(long id) {
        dao.borrar(id);
    }

    @Override
    public List<Tarea> listarTareas() {
        return dao.buscarTodos();
    }

}