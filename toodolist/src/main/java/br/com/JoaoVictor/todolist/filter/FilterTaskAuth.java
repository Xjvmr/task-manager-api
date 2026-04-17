package br.com.JoaoVictor.todolist.filter;

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
import java.util.Base64;

import static java.util.Base64.getDecoder;


@Component
public class FilterTaskAuth  extends OncePerRequestFilter {
    @Autowired
    private IUserRepository userRepository;
//filtros

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        //pegar a autenticação
        var autorization= request.getHeader("Authorization");

        var authEncoded = autorization.substring("Basic".length()).trim();

        byte [] authDecode = Base64.getDecoder().decode(authEncoded);

        var authString =new String(authDecode);

        String [] credentials = authString.split(":");
        String username = credentials[0];
        String password = credentials[1];


        // validae usuario
            var user = this.userRepository.findByuserName(username);
            if (user == null) {

            }

        // validae senha

        //seguinte
    }

}
