package edu.arquetipo.jpa.servicios;

import edu.arquetipo.jpa.entidades.Cliente;

public interface OperativaClienteInterfaz {

    Cliente crearCliente(Cliente cliente);

    Cliente buscarCliente(Long id);

    Cliente editarCliente(Long id, Cliente cliente, String cosaACambiar);

    boolean borrarCliente(Long id);

}
