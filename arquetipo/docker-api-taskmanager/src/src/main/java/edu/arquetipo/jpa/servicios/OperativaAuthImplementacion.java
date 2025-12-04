package edu.arquetipo.jpa.servicios;

import java.util.List;
import java.util.UUID;
import java.util.Base64;
import java.security.SecureRandom;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

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

    public String generarSecreto() {
        byte[] bytes = new byte[32]; // 256 bits
        new SecureRandom().nextBytes(bytes);
        return Base64.getEncoder().encodeToString(bytes);
    }

    @Override
    public boolean validarToken(String email, String token) {
        final String SECRET = generarSecreto();
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWT.require(algorithm)
                    .withIssuer("mi-api")
                    .build()
                    .verify(token);
            return true;
        } catch (JWTVerificationException e) {
            return false;
        }
    }

    @Override
    public String login(String email, String password) {

        String token = null;
        if (dao.buscarTodos().stream()
                .anyMatch(u -> u.getCorreo().equals(email) && u.getContrasena().equals(password))) {
            token = UUID.randomUUID().toString();
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
