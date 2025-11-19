package edu.arquetipo.jpa.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import edu.arquetipo.jpa.entidades.Subtarea;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

/**
 * Data Access Object (DAO) para la entidad Subtarea, utilizando JPA/Hibernate.
 * Se encarga de las operaciones CRUD y de búsqueda.
 * * NOTA: Esta implementación asume que la clave primaria de Subtarea es un
 * Long.
 */

@Repository
public class SubtareaDAO {

    // EntityManagerFactory es estática y final para asegurar que se crea una única
    // vez.
    @PersistenceContext
    private EntityManager em;

    /**
     * Busca una Subtarea por su clave primaria (ID).
     *
     * @param id La clave primaria de la subtarea.
     * @return El objeto Subtarea encontrado, o null si no existe.
     */
    @Transactional
    public Subtarea buscar(Long id) {
        return em.find(Subtarea.class, id);
    }

    /**
     * Busca una Subtarea por su nombre (utilizando JPQL).
     *
     * @param nombre El nombre exacto de la subtarea.
     * @return El objeto Subtarea encontrado, o null si no existe.
     */
    @Transactional
    public Subtarea buscarPorNombre(String nombre) {
        // Se utiliza una consulta JPQL parametrizada para buscar por un campo que no es
        // PK.
        String jpql = "SELECT s FROM Subtarea s WHERE s.nombre = :nombreSubtarea";
        TypedQuery<Subtarea> query = em.createQuery(jpql, Subtarea.class);
        query.setParameter("nombreSubtarea", nombre);

        // Retorna el resultado único. Si hay más de uno o ninguno, Hibernate maneja el
        // error.
        List<Subtarea> results = query.getResultList();
        return results.isEmpty() ? null : results.get(0);
    }

    /**
     * Inserta una nueva Subtarea en la base de datos.
     *
     * @param subtarea El objeto Subtarea a persistir.
     */
    @Transactional
    public void insertar(Subtarea subtarea) {
        em.persist(subtarea);
    }

    /**
     * Actualiza el nombre de una subtarea existente.
     *
     * @param id          El ID de la subtarea a actualizar.
     * @param nuevoNombre El nuevo nombre.
     */
    @Transactional
    public void actualizarNombre(Long id, String nuevoNombre) {
        Subtarea subtareaEncontrada = em.find(Subtarea.class, id);

        if (subtareaEncontrada != null) {
            subtareaEncontrada.setNombre(nuevoNombre);
            em.merge(subtareaEncontrada);
        }
    }

    /**
     * Borra una Subtarea de la base de datos usando su ID.
     *
     * @param id El ID de la subtarea a borrar.
     */
    @Transactional
    public void borrar(Long id) {
        Subtarea subtareaEncontrada = em.find(Subtarea.class, id);

        if (subtareaEncontrada != null) {
            em.remove(subtareaEncontrada);
        }
    }
}