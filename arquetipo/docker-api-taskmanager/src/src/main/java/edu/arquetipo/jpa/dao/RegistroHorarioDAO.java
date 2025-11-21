package edu.arquetipo.jpa.dao;

import java.time.LocalTime;

import org.springframework.stereotype.Repository;

import edu.arquetipo.jpa.entidades.RegistroHorario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
public class RegistroHorarioDAO {

    @PersistenceContext
    private EntityManager em;

    /**
     * Busca un RegistroHorario por su clave primaria (ID).
     *
     * @param id La clave primaria del registro.
     * @return El objeto RegistroHorario encontrado, o null si no existe.
     */
    @Transactional
    public RegistroHorario buscar(Long id) {
        return em.find(RegistroHorario.class, id);
    }

    /**
     * Inserta un nuevo RegistroHorario en la base de datos.
     *
     * @param registro El objeto RegistroHorario a persistir.
     */
    @Transactional
    public void insertar(RegistroHorario registro) {
        em.persist(registro);
    }

    /**
     * Actualiza la hora de CheckIn de un registro.
     *
     * @param id           El ID del registro a actualizar.
     * @param nuevoCheckIn La nueva hora de CheckIn.
     */
    @Transactional
    public void actualizarCheckIn(Long id, LocalTime nuevoCheckIn) {
        RegistroHorario registroEncontrado = em.find(RegistroHorario.class, id);

        if (registroEncontrado != null) {
            registroEncontrado.setCheckIn(nuevoCheckIn);
        }
    }

    /**
     * Actualiza la hora de CheckOut de un registro.
     *
     * @param id            El ID del registro a actualizar.
     * @param nuevoCheckOut La nueva hora de CheckOut.
     */
    @Transactional
    public void actualizarCheckOut(Long id, LocalTime nuevoCheckOut) {
        RegistroHorario registroEncontrado = em.find(RegistroHorario.class, id);

        if (registroEncontrado != null) {
            registroEncontrado.setCheckOut(nuevoCheckOut);
        }
    }
}