package br.com.wildrimak.jwt.api.security;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import br.com.wildrimak.jwt.domain.models.Role;
import br.com.wildrimak.jwt.domain.models.Usuario;
import br.com.wildrimak.jwt.domain.repositories.UsuarioRepository;

public class JWTValidarFilter extends BasicAuthenticationFilter {

    public static final String HEADER_ATRIBUTO = "Authorization";
    public static final String ATRIBUTO_PREFIXO = "Bearer ";
    
    private UsuarioRepository usuarioRepository;

    public JWTValidarFilter(AuthenticationManager authenticationManager,  UsuarioRepository usuarioRepository) {
	super(authenticationManager);
	this.usuarioRepository = usuarioRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
	    throws IOException, ServletException {

	String atributo = request.getHeader(HEADER_ATRIBUTO);

	if (atributo == null) {
	    chain.doFilter(request, response);
	    return;
	}

	if (!atributo.startsWith(ATRIBUTO_PREFIXO)) {
	    chain.doFilter(request, response);
	    return;
	}
	
	String token = atributo.replace(ATRIBUTO_PREFIXO, "");

	UsernamePasswordAuthenticationToken authenticationToken = getAuthenticationToken(token);

	SecurityContextHolder.getContext().setAuthentication(authenticationToken);
	chain.doFilter(request, response);

    }

    private UsernamePasswordAuthenticationToken getAuthenticationToken(String token) {

	String email = JWT.require(Algorithm.HMAC512(JWTAutenticarFilter.TOKEN_SENHA)).build().verify(token)
		.getSubject();
	

	if (email == null) {
	    return null;
	}
	
	Optional<Usuario> optional = usuarioRepository.findByEmail(email);
	Usuario usuario = optional.get();
	Role role = usuario.getRole();
	List<GrantedAuthority> authorities = role.getAuthorities();

	return new UsernamePasswordAuthenticationToken(email, null, authorities);

    }

}
