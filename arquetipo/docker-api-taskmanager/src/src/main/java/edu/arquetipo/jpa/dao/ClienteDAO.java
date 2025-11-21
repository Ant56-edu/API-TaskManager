package edu.arquetipo.jpa.dao;

import java.util.List;

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

    @Transactional
    public Cliente buscar(String nombre) {
        String hql = "SELECT c FROM Cliente c WHERE c.nombre = :nombre";
        List<Cliente> resultados = em.createQuery(hql, Cliente.class)
                .setParameter("nombre", nombre)
                .getResultList();
        return resultados.isEmpty() ? null : resultados.get(0);
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

    @Transactional
    public List<Cliente> mostrarListaClientes() {
        List<Cliente> clientes = em.createQuery("FROM Cliente", Cliente.class).getResultList();
        return clientes;
    }

    @Transactional
    public void actualizar(Long id, Cliente clienteActualizado) {
        Cliente clienteEncontrado = em.find(Cliente.class, id);
        if (clienteEncontrado != null) {
            clienteActualizado.setId(id);
            em.merge(clienteActualizado);
            em.close();
        }
    }
}