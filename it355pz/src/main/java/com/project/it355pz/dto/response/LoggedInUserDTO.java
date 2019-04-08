package com.project.it355pz.dto.response;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class LoggedInUserDTO {

	private int id;
	private String token;
	private String email;
	private Collection<? extends GrantedAuthority> authorities;

	public LoggedInUserDTO(){

	}

	public LoggedInUserDTO(int id, String token, String email, Collection<? extends GrantedAuthority> authorities) {
		super();
		this.id = id;
		this.token = token;
		this.email = email;
		this.authorities = authorities;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}
}
