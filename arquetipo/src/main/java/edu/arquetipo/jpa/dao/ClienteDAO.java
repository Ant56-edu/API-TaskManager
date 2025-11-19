package edu.arquetipo.jpa.dao;

import org.springframework.stereotype.Repository;

import edu.arquetipo.jpa.entidades.Cliente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
public class ClienteDAO {

    @PersistenceContext
    private EntityManager em;

    /**
     * Busca un Cliente por su clave primaria (ID).
     *
     * @param id La clave primaria del cliente.
     * @return El objeto Cliente encontrado, o null si no existe.
     */
    @Transactional
    public Cliente buscar(Long id) {
        return em.find(Cliente.class, id);
    }

    /**
     * Inserta un nuevo Cliente en la base de datos.
     *
     * @param cliente El objeto Cliente a persistir.
     */
    @Transactional
    public void insertar(Cliente cliente) {
        em.persist(cliente);
    }

    /**
     * Actualiza el nombre de un cliente.
     *
     * @param id          El ID del cliente a actualizar.
     * @param nuevoNombre El nuevo nombre para el cliente.
     */
    @Transactional
    public void actualizarNombre(Long id, String nuevoNombre) {
        Cliente clienteEncontrado = em.find(Cliente.class, id);

        if (clienteEncontrado != null) {
            clienteEncontrado.setNombre(nuevoNombre);
        }
    }

    /**
     * Actualiza el dominio web de un cliente.
     *
     * @param id           El ID del cliente a actualizar.
     * @param nuevoDominio El nuevo dominio web.
     */
    @Transactional
    public void actualizarDominio(Long id, String nuevoDominio) {
        Cliente clienteEncontrado = em.find(Cliente.class, id);
        if (clienteEncontrado != null) {
            clienteEncontrado.setDominioWeb(nuevoDominio);
        }
    }

    /**
     * Actualiza la dirección de un cliente.
     *
     * @param id             El ID del cliente a actualizar.
     * @param nuevaDireccion La nueva dirección.
     */
    @Transactional
    public void actualizarDireccion(Long id, String nuevaDireccion) {
        Cliente clienteEncontrado = em.find(Cliente.class, id);
        if (clienteEncontrado != null) {
            clienteEncontrado.setDireccion(nuevaDireccion);
        }
    }

    /**
     * Actualiza el teléfono de un cliente.
     *
     * @param id            El ID del cliente a actualizar.
     * @param nuevoTelefono El nuevo número de teléfono.
     */
    @Transactional
    public void actualizarTelefono(Long id, int nuevoTelefono) {
        Cliente clienteEncontrado = em.find(Cliente.class, id);
        if (clienteEncontrado != null) {
            clienteEncontrado.setTlf(nuevoTelefono);
        }
    }

    /**
     * Borra un Cliente de la base de datos usando su ID.
     *
     * @param id El ID del cliente a borrar.
     */
    @Transactional
    public boolean borrarCliente(Long id) {
        boolean borrado = false;
        Cliente clienteEncontrado = em.find(Cliente.class, id);

        if (clienteEncontrado != null) {
            em.remove(clienteEncontrado);
            borrado = true;
        }
        return borrado;
    }
}