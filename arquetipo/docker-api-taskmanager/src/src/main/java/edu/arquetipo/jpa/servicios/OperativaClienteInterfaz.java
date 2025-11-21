package edu.arquetipo.jpa.servicios;

import java.util.List;

import edu.arquetipo.jpa.entidades.Cliente;

public interface OperativaClienteInterfaz {

    Cliente crearCliente(Cliente cliente);

    Cliente buscarCliente(String nombre);

    Cliente buscarCliente(Long id);

    Cliente editarCliente(Long id, Cliente clienteActualizado);

    boolean borrarCliente(Long id);

    List<Cliente> mostrarListaClientes();

}
