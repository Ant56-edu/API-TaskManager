package edu.arquetipo.jpa.dao;

import org.springframework.stereotype.Repository;

import edu.arquetipo.jpa.entidades.Tarea;
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
     * Edita los detalles de una Tarea en la base de datos usando su ID.
     *
     * @param id El ID de la tarea a editar.
     */
    @Transactional
    public void editar(Long id, Tarea tareaActualizada) {
        Tarea tareaEncontrada = em.find(Tarea.class, id);

        if (tareaEncontrada != null) {
            tareaActualizada.setId(id);
            em.merge(tareaActualizada);
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