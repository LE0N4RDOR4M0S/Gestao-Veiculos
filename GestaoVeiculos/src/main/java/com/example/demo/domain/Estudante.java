package com.example.demo.domain;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Component
@Entity
public class Estudante {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idMatricula;
	@Column(length = 100, nullable = false)
	String nome;
	@Column(length = 100, nullable = false)
	String turno;
	@Column(length = 100, nullable = false)
	boolean acessibilidade;
	@Column (length = 100, nullable = false)
	String enderecoResidencial;
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(nullable=false)
	Rota rota;
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn (nullable = false)
	Parada parada;
	
	public Estudante(Integer idMatricula, String nome, String turno, boolean acessibilidade, String enderecoResidencial,
			Rota rotaId, Parada paradaId) {
		super();
		this.idMatricula = idMatricula;
		this.nome = nome;
		this.turno = turno;
		this.acessibilidade = acessibilidade;
		this.enderecoResidencial = enderecoResidencial;
		this.rota = rotaId;
		this.parada = paradaId;
	}
	public Estudante() {
		super();
	}
	public Integer getIdMatricula() {
		return idMatricula;
	}
	public void setIdMatricula(Integer idMatricula) {
		this.idMatricula = idMatricula;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTurno() {
		return turno;
	}
	public void setTurno(String turno) {
		this.turno = turno;
	}
	public boolean isAcessibilidade() {
		return acessibilidade;
	}
	public void setAcessibilidade(boolean acessibilidade) {
		this.acessibilidade = acessibilidade;
	}
	public String getEnderecoResidencial() {
		return enderecoResidencial;
	}
	public void setEnderecoResidencial(String enderecoResidencial) {
		this.enderecoResidencial = enderecoResidencial;
	}
	public Rota getRota() {
		return rota;
	}
	public void setRota(Rota rota) {
		this.rota = rota;
	}
	public Parada getParada() {
		return parada;
	}
	public void setParada(Parada parada) {
		this.parada = parada;
	}
	
	
	
}
