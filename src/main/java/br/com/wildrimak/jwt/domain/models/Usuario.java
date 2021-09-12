package br.com.wildrimak.jwt.domain.models;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email
    @NotEmpty
    private String email;

    @NotEmpty
    private String password;

    @NotEmpty
    private String nome;

    @Enumerated(EnumType.STRING)
    private Role role;

    private Usuario() {
	this.role = Role.NORMAL;
    }

    public Usuario(@Email @NotEmpty String email, @NotEmpty String password, @NotEmpty String nome) {
	this();
	this.email = email;
	this.password = password;
	this.nome = nome;
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public String getNome() {
	return nome;
    }

    public void setNome(String nome) {
	this.nome = nome;
    }

    public Role getRole() {
	return role;
    }
    
    public void setRole(Role role) {
	this.role = role;
    }

}
