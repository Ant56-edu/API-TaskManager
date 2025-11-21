package edu.arquetipo.jpa.controladores;

import edu.arquetipo.jpa.entidades.Usuario;
import edu.arquetipo.jpa.servicios.OperativaAuthInterfaz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServlet;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;

import com.eatthepath.otp.TimeBasedOneTimePasswordGenerator;

@RestController
@RequestMapping("/api/auth")
public class AuthServlet extends HttpServlet {

    private final OperativaAuthInterfaz auth;

    @Autowired
    public AuthServlet(OperativaAuthInterfaz auth) {
        this.auth = auth;
    }

    // DTOs
    public static class LoginCredentials {
        public String email;
        public String password;
    }

    public static class RecoveryRequest {
        public String email;
    }

    public static class PasswordChangeRequest {
        public String email;
        public String newPassword;
        public String token; // OTP
    }

    /**
     * Login de usuario
     */
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginCredentials creds) {
        String token = auth.login(creds.email, creds.password);

        if (token != null) {
            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Credenciales inválidas"));
        }
    }

    /**
     * Solicitar recuperación de contraseña
     */
    @PostMapping("/recover-password")
    public ResponseEntity<Map<String, String>> recoverPassword(@RequestBody RecoveryRequest request)
            throws NoSuchAlgorithmException, InvalidKeyException {

        Usuario usuario = auth.buscarUsuarioPorEmail(request.email);

        if (usuario != null) {
            // Generamos token OTP usando TOTP
            TimeBasedOneTimePasswordGenerator totp = new TimeBasedOneTimePasswordGenerator();
            Key key;
            {
                KeyGenerator keyGenerator = KeyGenerator.getInstance(totp.getAlgorithm());
                int macLengthInBytes = Mac.getInstance(totp.getAlgorithm()).getMacLength();
                keyGenerator.init(macLengthInBytes * 8);
                key = keyGenerator.generateKey();
            }

            Instant now = Instant.now();
            Instant later = now.plus(totp.getTimeStep());
            int oneTimePassword = totp.generateOneTimePassword(key, later);

            // Enviar token por correo se asume en auth.enviarTokenCorreo()
            auth.enviarTokenPorCorreo(usuario.getCorreo(), oneTimePassword);

            return ResponseEntity
                    .ok(Map.of("message", "Se ha enviado un token de recuperación a su correo electrónico."));
        } else {
            return ResponseEntity.ok(Map.of("message", "Este correo no está registrado."));
        }
    }

    /**
     * Cambio de contraseña usando token OTP
     */
    @PostMapping("/change-password")
    public ResponseEntity<Map<String, String>> changePassword(@RequestBody PasswordChangeRequest request) {

        if (request.email == null || request.token == null || request.newPassword == null) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "Faltan campos obligatorios: email, token o nueva contraseña."));
        }

        Usuario usuario = auth.buscarUsuarioPorEmail(request.email);

        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Usuario no encontrado."));
        }

        // Validación del token OTP (se asume en auth.validarToken)
        boolean tokenValido = auth.validarToken(request.email, request.token);

        if (!tokenValido) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Token inválido o expirado."));
        }

        // Actualizamos la contraseña usando la capa de servicio
        usuario.setContrasena(request.newPassword);

        return ResponseEntity.ok(Map.of("message", "Contraseña actualizada exitosamente."));
    }
}