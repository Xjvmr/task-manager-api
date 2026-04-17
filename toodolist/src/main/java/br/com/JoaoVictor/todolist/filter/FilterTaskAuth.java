package br.com.JoaoVictor.todolist.filter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.JoaoVictor.todolist.user.IUserRepository;
import jakarta.servlet.*;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Component
public class FilterTaskAuth  extends OncePerRequestFilter {
    @Autowired
    private IUserRepository userRepository;
//filtros

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        var servletPath = request.getServletPath();
        if (!servletPath.startsWith("/tasks")) {
            filterChain.doFilter(request, response);
            return;
        }

        // pegar a autenticacao
        var authorization = request.getHeader("Authorization");
        if (authorization == null || !authorization.startsWith("Basic ")) {
            response.sendError(401, "Authorization ausente ou invalida.");
            return;
        }

        var authEncoded = authorization.substring("Basic".length()).trim();
        byte[] authDecode;
        try {
            authDecode = Base64.getDecoder().decode(authEncoded);
        } catch (IllegalArgumentException ex) {
            response.sendError(401, "Authorization Basic invalida.");
            return;
        }

        var authString = new String(authDecode, StandardCharsets.UTF_8);
        String[] credentials = authString.split(":", 2);
        if (credentials.length < 2) {
            response.sendError(401, "Credenciais invalidas.");
            return;
        }

        String username = credentials[0];
        String password = credentials[1];

        // validar usuario
        var user = this.userRepository.findByuserName(username);
        if (user == null) {
            response.sendError(401, "Usuario ou senha invalidos.");
            return;
        }

        // validar senha
        var passwordVerify = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());
        if (!passwordVerify.verified) {
            response.sendError(401, "Usuario ou senha invalidos.");
            return;
        }

        filterChain.doFilter(request, response);
    }

}
