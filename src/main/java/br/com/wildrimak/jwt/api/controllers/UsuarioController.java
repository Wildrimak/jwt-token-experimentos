package br.com.wildrimak.jwt.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.wildrimak.jwt.domain.models.Usuario;
import br.com.wildrimak.jwt.domain.repositories.UsuarioRepository;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioRepository usuarioRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public List<Usuario> getUsers() {

	return usuarioRepository.findAll();

    }

    @PostMapping
    public Usuario saveUsuario(@Valid @RequestBody Usuario usuario) {
	
	usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
	return usuarioRepository.save(usuario);
    }

}
