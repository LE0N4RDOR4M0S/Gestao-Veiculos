package com.example.demo.domain;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Component
@Entity
public class Veiculo {
	@Id
	private String placa;
	@Column(length = 100, nullable = false)
	String tipo;
	@Column(length = 100, nullable = false)
	Boolean acessibilidade;
	@Column(length = 100, nullable = false)
	String estacionamento;
	@Column(length = 100, nullable = false)
	String modelo;
	@Column(length = 100, nullable = false)
	int ano;
	@Column(length = 100, nullable = false, unique = true)
	String renavam;
	@Column(length = 100, nullable = false)
	int qtd_lugares;
	@JoinColumn( nullable = false)
	@ManyToOne
	Rota rota;
	@Column(length = 100, nullable = false)
	String status;
	@JsonIgnore
	@OneToMany(mappedBy = "veiculo", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private Set<Motorista> motorista = new HashSet<Motorista>();
	@JsonIgnore
	@OneToMany(mappedBy = "veiculo", cascade = CascadeType.ALL)
	private Set<Manutencao> manutencao = new HashSet<Manutencao>();
	@JsonIgnore
	@OneToMany(mappedBy = "veiculo", cascade = CascadeType.ALL)
	private Set<Multa> multa = new HashSet<Multa>();
	
	public Veiculo(String placa, String tipo, Boolean acessibilidade, String estacionamento, String modelo, int ano,
			String renavam, int qtd_lugares, Rota rotaId, String status) {
		super();
		this.placa = placa;
		this.tipo = tipo;
		this.acessibilidade = acessibilidade;
		this.estacionamento = estacionamento;
		this.modelo = modelo;
		this.ano = ano;
		this.renavam = renavam;
		this.qtd_lugares = qtd_lugares;
		this.rota = rotaId;
		this.status = status;
	}
	public Veiculo() {
		super();
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Boolean getAcessibilidade() {
		return acessibilidade;
	}
	public void setAcessibilidade(Boolean acessibilidade) {
		this.acessibilidade = acessibilidade;
	}
	public String getEstacionamento() {
		return estacionamento;
	}
	public void setEstacionamento(String estacionamento) {
		this.estacionamento = estacionamento;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	public String getRenavam() {
		return renavam;
	}
	public void setRenavam(String renavam) {
		this.renavam = renavam;
	}
	public int getQtd_lugares() {
		return qtd_lugares;
	}
	public void setQtd_lugares(int qtd_lugares) {
		this.qtd_lugares = qtd_lugares;
	}
	public Rota getRota() {
		return rota;
	}
	public void setRota(Rota rota) {
		this.rota = rota;
	}
	
	public String getStatus() {
	    for (Manutencao manutencaoItem : manutencao) {
	        if (manutencaoItem.getDataSaida() == null) {
	            return "manutencao";
	        }
	    }

	    return status;
	}

	public void setStatus(String status){
	    this.status = status;
	}
	
	public Set<Motorista> getMotorista() {
		return motorista;
	}
	public void setMotorista(Set<Motorista> motorista) {
		this.motorista = motorista;
	}
	
	
	
	
}
