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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Component
@Entity
public class Rota {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(length = 100, nullable = false)
	String origem;
	@Column(length = 100, nullable = false)
	String destino;
	@ManyToOne
	@JoinColumn(name="gestor", nullable = true)
	Gestor gestor;
	@JsonIgnore 
	@OneToMany(mappedBy = "rota", cascade = CascadeType.ALL)
	private Set<Veiculo> veiculo = new HashSet<Veiculo>();
	@JsonIgnore
	@OneToMany(mappedBy = "rota", cascade = CascadeType.ALL)
	private Set<Motorista> motorista = new HashSet<Motorista>();
	@JsonIgnore
	@OneToMany(mappedBy = "rota", cascade = CascadeType.REMOVE)
	private Set<Itinerario> itinerario = new HashSet<Itinerario>();
	@JsonIgnore
	@OneToMany(mappedBy = "rota", cascade = CascadeType.ALL)
	private Set<Estudante> estudante = new HashSet<Estudante>();
	
	public Rota(Integer id, String origem, String destino, Gestor idGestor) {
		super();
		this.id = id;
		this.origem = origem;
		this.destino = destino;
		this.gestor = idGestor;
	}

	public Rota() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrigem() {
		return origem;
	}

	public void setOrigem(String origem) {
		this.origem = origem;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public Gestor getGestor() {
		return gestor;
	}

	public void setGestor(Gestor idGestor) {
		this.gestor = idGestor;
	}

	public Set<Estudante> getEstudante() {
		return estudante;
	}

	public void setEstudante(Set<Estudante> estudante) {
		this.estudante = estudante;
	}

	public Set<Veiculo> getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Set<Veiculo> veiculo) {
		this.veiculo = veiculo;
	}

	public Set<Motorista> getMotorista() {
		return motorista;
	}

	public void setMotorista(Set<Motorista> motorista) {
		this.motorista = motorista;
	}

	public Set<Itinerario> getItinerario() {
		return itinerario;
	}

	public void setItinerario(Set<Itinerario> itinerario) {
		this.itinerario = itinerario;
	}
	
	
	
	
	
}
