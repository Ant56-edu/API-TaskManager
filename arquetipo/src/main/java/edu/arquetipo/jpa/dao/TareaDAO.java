package edu.arquetipo.jpa.dao;

import java.util.Set;

import org.springframework.stereotype.Repository;

import edu.arquetipo.jpa.entidades.Tarea;
import edu.arquetipo.jpa.entidades.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
public class TareaDAO {

    @PersistenceContext
    private EntityManager em;

    /**
     * Busca una Tarea por su clave primaria (ID).
     *
     * @param id La clave primaria de la tarea.
     * @return El objeto Tarea encontrado, o null si no existe.
     */
    @Transactional
    public Tarea buscar(Long id) {
        return em.find(Tarea.class, id);
    }

    /**
     * Inserta una nueva Tarea en la base de datos.
     *
     * @param tarea El objeto Tarea a persistir.
     */
    @Transactional
    public void insertar(Tarea tarea) {
        em.persist(tarea);
    }

    /**
     * Actualiza el nombre de una tarea.
     *
     * @param id          El ID de la tarea a actualizar.
     * @param nuevoNombre El nuevo nombre para la tarea.
     */
    @Transactional
    public void actualizarNombre(Long id, String nuevoNombre) {
        Tarea tareaEncontrada = em.find(Tarea.class, id);

        if (tareaEncontrada != null) {
            tareaEncontrada.setNombre(nuevoNombre);
        }
    }

    /**
     * Reemplaza la lista de empleados asignados a una tarea.
     *
     * @param id              El ID de la tarea.
     * @param nuevosEmpleados La nueva lista de empleados (o null/vacía).
     */
    @Transactional
    public void actualizarEmpleadosAsignados(Long id, Set<Usuario> nuevosEmpleados) {
        Tarea tareaEncontrada = em.find(Tarea.class, id);

        if (tareaEncontrada != null) {
            tareaEncontrada.setEmpleadosAsignados(nuevosEmpleados);
        }
    }

    /**
     * Actualiza el gestor encargado de una tarea.
     *
     * @param id          El ID de la tarea.
     * @param nuevoGestor El objeto Usuario que será el nuevo gestor.
     */
    @Transactional
    public void actualizarGestor(Long id, Usuario nuevoGestor) {
        Tarea tareaEncontrada = em.find(Tarea.class, id);

        if (tareaEncontrada != null) {
            tareaEncontrada.setGestorEncargado(nuevoGestor);
        }
    }

    /**
     * Actualiza el estado de una tarea (e.g., a "Completada", "Bloqueada", etc.).
     *
     * @param id          El ID de la tarea.
     * @param nuevoEstado El nuevo valor del estado (e.g., un String o Enum).
     */
    @Transactional
    public void actualizarEstado(Long id, String nuevoEstado) {
        Tarea tareaEncontrada = em.find(Tarea.class, id);

        if (tareaEncontrada != null) {
            tareaEncontrada.setEstadoTarea(nuevoEstado);
        }
    }

    /**
     * Borra una Tarea de la base de datos usando su ID.
     *
     * @param id El ID de la tarea a borrar.
     */
    @Transactional
    public void borrar(Long id) {
        Tarea tareaEncontrada = em.find(Tarea.class, id);

        if (tareaEncontrada != null) {
            em.remove(tareaEncontrada);
        }
    }
}