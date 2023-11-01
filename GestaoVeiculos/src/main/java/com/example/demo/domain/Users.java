package com.example.demo.domain;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="users")
public class Users implements UserDetails{
	/**
	 *
	 */
	private static final long serialVersionUID = 3500907109482635437L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(unique = true)
	String login;
	@Column(nullable = false)
	String password;
	@Column(nullable = false)
	UserRole role;
	@Column(nullable = false)
	String idTipoUsuario;
	
	public Users() {
		super();
	}
	
	public Users(String login, String password, UserRole role, String idTipoUsuario) {
		super();
		this.login = login;
		this.password = password;
		this.role = role;
		this.idTipoUsuario = idTipoUsuario;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if(this.role == UserRole.ADMIN) { return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"), new SimpleGrantedAuthority("ROLE_ESTUDANTE"));}
		else if(this.role == UserRole.USER) { return List.of(new SimpleGrantedAuthority("ROLE_USER"), new SimpleGrantedAuthority("USER_ESTUDANTE"));}
		else {return List.of(new SimpleGrantedAuthority("ROLE_ESTUDANTE"));}
	}
	@Override
	public String getPassword() {
	
		return password;
	}
	@Override
	public String getUsername() {

		return login;
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

	public String getIdTipoUsuario() {
		return idTipoUsuario;
	}

	public void setIdTipoUsuario(String idTipoUsuario) {
		this.idTipoUsuario = idTipoUsuario;
	}

	public UserRole getRole() {
		return role;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}
	
	
}
