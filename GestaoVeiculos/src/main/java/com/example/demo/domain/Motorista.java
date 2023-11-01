package com.example.demo.domain;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Component
@Entity
public class Motorista {
	@Id
	private String cnh;
	@Column(length = 100, nullable = false)
	String nome;
	@Column(length = 100, nullable = false)
	String endereco;
	@Column(length = 100, nullable = false)
	String contato;
	@Column(length = 100, nullable = false)
	String turno;
	@JoinColumn(nullable = false)
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	Rota rota;
	@JoinColumn(name="veiculo_id", nullable = false)
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	Veiculo veiculo;
	
	public Motorista(String cnh, String nome, String endereco, String contato, String turno, Rota rotaId,
			Veiculo veiculoId) {
		super();
		this.cnh = cnh;
		this.nome = nome;
		this.endereco = endereco;
		this.contato = contato;
		this.turno = turno;
		this.rota = rotaId;
		this.veiculo = veiculoId;
	}

	public Motorista() {
		super();
	}

	public String getCnh() {
		return cnh;
	}

	public void setCnh(String cnh) {
		this.cnh = cnh;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
	}

	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}

	public Rota getRota() {
		return rota;
	}

	public void setRota(Rota rota) {
		this.rota = rota;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}
	
	
	
}