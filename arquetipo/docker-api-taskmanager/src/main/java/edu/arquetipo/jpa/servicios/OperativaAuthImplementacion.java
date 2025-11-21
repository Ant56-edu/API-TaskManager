package edu.arquetipo.jpa.servicios;

import java.util.List;

import org.springframework.stereotype.Service;

import edu.arquetipo.jpa.dao.UsuarioDAO;
import edu.arquetipo.jpa.entidades.Usuario;

@Service
public class OperativaAuthImplementacion implements OperativaAuthInterfaz {

    private final UsuarioDAO dao;

    public OperativaAuthImplementacion(UsuarioDAO dao) {
        this.dao = dao;
    }

    @Override
    public String enviarTokenPorCorreo(String correo, int otp) {
        System.out.println("Enviando OTP " + otp + " al correo: " + correo);
        return "Token enviado al correo: " + correo;
    }

    @Override
    public boolean validarToken(String email, String tokenRecibido) {
        return true;
    }

    @Override
    public String login(String email, String password) {

        String token = null;
        if (dao.buscarTodos().stream()
                .anyMatch(u -> u.getCorreo().equals(email) && u.getContrasena().equals(password))) {
            token = "token-de-ejemplo-12345";
        }
        return token;
    }

    @Override
    public Usuario buscarUsuarioPorEmail(String email) {
        List<Usuario> usuarios = dao.buscarTodos();
        for (Usuario usuario : usuarios) {
            if (usuario.getCorreo().equals(email)) {
                return usuario;
            }
        }
        return null;
    }

}
