package edu.arquetipo.jpa.dao;

import org.springframework.stereotype.Repository;

import edu.arquetipo.jpa.entidades.Comentario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
public class ComentarioDAO {

    @PersistenceContext
    private EntityManager em;

    /**
     * Busca un Comentario por su clave primaria (ID).
     *
     * @param id La clave primaria del comentario.
     * @return El objeto Comentario encontrado, o null si no existe.
     */
    @Transactional
    public Comentario buscar(Long id) {
        return em.find(Comentario.class, id);
    }

    /**
     * Inserta un nuevo Comentario en la base de datos.
     *
     * @param comentario El objeto Comentario a persistir.
     */
    @Transactional
    public void insertar(Comentario comentario) {
        em.persist(comentario);
    }

    /**
     * Actualiza el contenido de un Comentario existente.
     *
     * @param idComentario   El ID del comentario a actualizar.
     * @param nuevoContenido El nuevo texto para el comentario.
     */
    @Transactional
    public void actualizarContenido(Long idComentario, String nuevoContenido) {
        Comentario comentarioEncontrado = em.find(Comentario.class, idComentario);
        if (comentarioEncontrado != null) {
            comentarioEncontrado.setContenido(nuevoContenido);
        }
    }

    /**
     * Borra un Comentario de la base de datos usando su ID.
     *
     * @param id El ID del comentario a borrar.
     */
    @Transactional
    public void borrarComentario(Long id) {
        Comentario comentarioEncontrado = em.find(Comentario.class, id);

        if (comentarioEncontrado != null) {
            em.remove(comentarioEncontrado);
        }
    }
}