package edu.arquetipo.jpa.servicios;

import java.util.List;

import org.springframework.stereotype.Service;

import edu.arquetipo.jpa.dao.ClienteDAO;
import edu.arquetipo.jpa.entidades.Cliente;

@Service
public class OperativaClienteImplementacion implements OperativaClienteInterfaz {

    private final ClienteDAO dao;

    public OperativaClienteImplementacion(ClienteDAO dao) {
        this.dao = dao;
    }

    @Override
    public List<Cliente> mostrarListaClientes() {
        return dao.mostrarListaClientes();
    }

    @Override
    public Cliente crearCliente(Cliente cliente) {
        dao.insertar(cliente);
        return dao.buscar(cliente.getNombre());
    }

    @Override
    public Cliente buscarCliente(String nombre) {
        return dao.buscar(nombre);
    }

    @Override
    public Cliente editarCliente(Long id, Cliente clienteActualizado) {
        dao.actualizar(id, clienteActualizado);
        return dao.buscar(id);
    }

    @Override
    public boolean borrarCliente(Long id) {
        return dao.borrarCliente(id);
    }

    @Override
    public Cliente buscarCliente(Long id) {
        return dao.buscar(id);
    }

}
