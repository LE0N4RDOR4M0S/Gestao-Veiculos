package com.example.demo.domain;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Component
@Entity
public class Gestor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Id;
	@Column(length = 100, nullable = false)
	String email;
	@Column (length = 100, nullable = false)
	String nome;
	@JsonIgnore
	@OneToMany(mappedBy = "gestor", cascade = CascadeType.ALL)
	private Set<Rota> rota = new HashSet<Rota>();
	
	public Gestor(Integer id, String email, String nome) {
		super();
		Id = id;
		this.email = email;
		this.nome = nome;
	}
	public Gestor() {
		super();
	}
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Set<Rota> getRota() {
		return rota;
	}
	public void setRota(Set<Rota> rota) {
		this.rota = rota;
	}
	
	
}
