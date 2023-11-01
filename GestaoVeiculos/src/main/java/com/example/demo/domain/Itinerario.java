package com.example.demo.domain;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Component
@Entity
public class Itinerario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Id;
	@JoinColumn(name="rota", nullable = false)
	@ManyToOne()
	Rota rota;
	@JoinColumn(nullable = false)
	@ManyToOne
	Parada parada;
	@Column(nullable = false)
	String hora;
	
	public Itinerario(int id, Rota rotaId, Parada paradaId, String hora) {
		super();
		Id = id;
		this.rota = rotaId;
		this.parada = paradaId;
		this.hora = hora;
	}

	public Itinerario() {
		super();
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public Rota getRota() {
		return rota;
	}

	public void setRota(Rota rotaId) {
		this.rota = rotaId;
	}

	public Parada getParada() {
		return parada;
	}

	public void setParada(Parada paradaId) {
		this.parada = paradaId;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}
	
	

}
