package edu.arquetipo.jpa.servicios;

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
    public Cliente crearCliente(Cliente cliente) {
        dao.insertar(cliente);
        return dao.buscar(cliente.getId());
    }

    @Override
    public Cliente buscarCliente(Long id) {
        return dao.buscar(id);
    }

    @Override
    public Cliente editarCliente(Long id, Cliente cliente, String cosaACambiar) {
        switch (cosaACambiar) {
            case "nombre":
                dao.actualizarNombre(id, cliente.getNombre());
                break;
            case "dominioWeb":
                dao.actualizarDominio(id, cliente.getDominioWeb());
                break;
            case "direccion":
                dao.actualizarDireccion(id, cliente.getDireccion());
                break;
            case "telefono":
                dao.actualizarTelefono(id, cliente.getTlf());
                break;
            default:
                System.out.println("Opción no válida");
        }
        return dao.buscar(id);
    }

    @Override
    public boolean borrarCliente(Long id) {
        return dao.borrarCliente(id);
    }

}
