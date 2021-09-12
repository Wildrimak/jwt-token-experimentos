package br.com.wildrimak.jwt.api.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import br.com.wildrimak.jwt.domain.models.Usuario;
import br.com.wildrimak.jwt.domain.repositories.UsuarioRepository;

@Component
public class DetalheUsuarioServiceImpl implements UserDetailsService{

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
	Optional<Usuario> optional = usuarioRepository.findByEmail(username);
	
	if (optional.isEmpty()) {
	    throw new UsernameNotFoundException("Email [" + username + "] não encontrado");
	}
	
	return new DetalheUsuarioData(optional);
    }

}
