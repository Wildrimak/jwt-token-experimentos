package br.com.wildrimak.jwt.api.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.wildrimak.jwt.api.data.DetalheUsuarioData;
import br.com.wildrimak.jwt.domain.models.Usuario;

public class JWTAutenticarFilter extends UsernamePasswordAuthenticationFilter {

    public static final int TOKEN_EXPIRATION = 600000;
    public static final String TOKEN_SENHA = "e3787bb8-b70f-4432-b7a3-3a283a50ceee";
    
    private final AuthenticationManager authenticationManager;

    public JWTAutenticarFilter(AuthenticationManager authenticationManager) {
	this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
	
	try {
	
	    Usuario usuario = new ObjectMapper().readValue(request.getInputStream(), Usuario.class);

	    return 
		    authenticationManager.authenticate(
			    new UsernamePasswordAuthenticationToken(
				    usuario.getEmail(), 
				    usuario.getPassword(),
				    new ArrayList<>()));
	
	} catch (IOException e) {
	    throw new RuntimeException("Falha ao autenticar usuário: ", e);
	}
	
    }
    
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
            Authentication authResult) throws IOException, ServletException {
	
	
	DetalheUsuarioData usuarioData = (DetalheUsuarioData) authResult.getPrincipal();
	
	String token = JWT.create()
		.withSubject(usuarioData.getUsername())
		.withExpiresAt(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION))
		.sign(Algorithm.HMAC512(TOKEN_SENHA));
	
	response.getWriter().write(token);
	response.getWriter().flush();
	
    }

}