package br.com.wildrimak.jwt.domain.models;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ADMIN, NORMAL;

    @Override
    public String getAuthority() {
	return this.name();
    }

    public List<GrantedAuthority> getAuthorities() {

	List<GrantedAuthority> list = new ArrayList<>();
	list.add(this);

	return list;

    }

}
