package com.example.demo.domain;

import java.util.Date;

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
public class Multa {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Id;
	@Column(length = 100, nullable = false)
	Date data;
	@Column(length = 100, nullable = false)
	Double valor;
	@Column(length = 100, nullable = false)
	String descricao;
	@JoinColumn(name="veiculo", nullable = false)
	@ManyToOne
	Veiculo veiculo;
	
	public Multa(int id, Date data, Double valor, String descricao, Veiculo placaVeiculo) {
		super();
		Id = id;
		this.data = data;
		this.valor = valor;
		this.descricao = descricao;
		this.veiculo = placaVeiculo;
	}
	public Multa() {
		super();
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Veiculo getVeiculo() {
		return veiculo;
	}
	public void setVeiculo(Veiculo placaVeiculo) {
		this.veiculo = placaVeiculo;
	}
	
	
	
}
