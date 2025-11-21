package edu.arquetipo.jpa.servicios;

import edu.arquetipo.jpa.entidades.Usuario;

public interface OperativaAuthInterfaz {

    Usuario buscarUsuarioPorEmail(String email);

    String enviarTokenPorCorreo(String correo, int otp);

    boolean validarToken(String email, String token);

    String login(String email, String password);

}
