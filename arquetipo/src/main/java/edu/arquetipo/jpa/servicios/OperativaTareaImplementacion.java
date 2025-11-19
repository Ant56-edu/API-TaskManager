package edu.arquetipo.jpa.servicios;

import java.util.Set;

import org.springframework.stereotype.Service;

import edu.arquetipo.jpa.dao.TareaDAO;
import edu.arquetipo.jpa.entidades.Tarea;
import edu.arquetipo.jpa.entidades.Usuario;

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
        return dao.buscar(tarea.getId());
    }

    @Override
    public Tarea editarDetalles(long id, String cosaACambiar) {
        switch (cosaACambiar) {
            case "gestor" -> {
                Usuario nuevoGestor = null;
                dao.actualizarGestor(id, nuevoGestor);
                return dao.buscar(id);
            }
            case "empleados" -> {
                Set<Usuario> nuevosEmpleados = null;
                dao.actualizarEmpleadosAsignados(id, nuevosEmpleados);
                return dao.buscar(id);
            }
            case "estado" -> {
                String estado = "ESTADO_PENDIENTE_ACTUALIZACION";
                dao.actualizarEstado(id, estado);
                return dao.buscar(id);
            }
            case "nombre" -> {
                // El nuevo nombre (String) debería venir como parámetro. Se usa un marcador.
                String nuevoNombre = "NOMBRE_PENDIENTE_ACTUALIZACION";
                dao.actualizarNombre(id, nuevoNombre);
                return dao.buscar(id);
            }
            default -> {
                System.err.println("Campo de actualización no reconocido: " + cosaACambiar);
                return dao.buscar(id);
            }
        }
    }

    @Override
    public void borrarTarea(long id) {
        dao.borrar(id);
    }

}