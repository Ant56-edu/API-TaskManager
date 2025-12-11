package edu.arquetipo.jpa.servicios;

import java.util.List;

import org.springframework.stereotype.Service;

import edu.arquetipo.jpa.dao.SubtareaDAO;
import edu.arquetipo.jpa.entidades.Subtarea;

@Service
public class OperativaSubtareaImplementacion implements OperativaSubtareaInterfaz {

    private final SubtareaDAO dao;

    public OperativaSubtareaImplementacion(SubtareaDAO dao) {
        this.dao = dao;
    }

    @Override
    public Subtarea crearSubtarea(Subtarea subtarea) {
        dao.insertar(subtarea);
        return dao.buscar(subtarea.getId());
    }

    @Override
    public Subtarea buscarSubtarea(String nombre) {
        return dao.buscarPorNombre(nombre);
    }

    @Override
    public List<Subtarea> listarTodasSubtareas() {
        return dao.buscarTodos();
    }

    @Override
    public Subtarea editarSubtarea(String nombre, Subtarea subtareaActualizada) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'editarSubtarea'");
    }

    @Override
    public boolean borrarSubtarea(String nombre) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'borrarSubtarea'");
    }

}
