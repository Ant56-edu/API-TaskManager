package edu.arquetipo.jpa.servicios;

import java.util.List;

import org.springframework.stereotype.Service;

import edu.arquetipo.jpa.entidades.Subtarea;

@Service
public class OperativaSubtareaImplementacion implements OperativaSubtareaInterfaz {

    @Override
    public Subtarea crearSubtarea(Subtarea subtarea) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'crearSubtarea'");
    }

    @Override
    public Subtarea buscarSubtarea(String nombre) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarSubtarea'");
    }

    @Override
    public List<Subtarea> listarTodasSubtareas() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listarTodasSubtareas'");
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
