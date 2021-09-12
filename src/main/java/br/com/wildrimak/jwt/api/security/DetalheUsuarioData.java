package br.com.wildrimak.jwt.api.security;

import java.util.Collection;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.wildrimak.jwt.domain.models.Usuario;

public class DetalheUsuarioData implements UserDetails {

    private static final long serialVersionUID = -8185214826964718663L;

    private Optional<Usuario> usuarioOptional;

    public DetalheUsuarioData(Optional<Usuario> usuarioOptional) {
	this.usuarioOptional = usuarioOptional;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
	return usuarioOptional.orElse(new Usuario("email@gmail.com", "pass", "nome")).getRole().getAuthorities();
    }

    @Override
    public String getPassword() {
	return usuarioOptional.orElse(new Usuario("email@gmail.com", "pass", "nome")).getPassword();
    }

    @Override
    public String getUsername() {
	return usuarioOptional.orElse(new Usuario("email@gmail.com", "pass", "nome")).getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
	return true;
    }

    @Override
    public boolean isAccountNonLocked() {
	return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
	return true;
    }

    @Override
    public boolean isEnabled() {
	return true;
    }

}
